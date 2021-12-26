import math
import pandas as pd


class Node:
    def __init__(self):
        self.attribute = 0
        self.value = None
        self.isRec = True
        self.isLeaf = False
        self.children = list()
        self.parent = None


class DataSet:
    def __init__(self, dataSet):
        self.dataSet = dataSet
        self.cntRec, self.cntNoRec = self.getRecAndNoRecCount()

    def entropy(self, positive, negative):
        if positive == 0 or negative == 0:
            return 0
        return - ((positive / (positive + negative)) * math.log(positive / (positive + negative), 2)) - (
                (negative / (positive + negative)) * math.log(negative / (positive + negative), 2))

    def getRecAndNoRecCount(self):
        cntRec = len(self.dataSet.loc[self.dataSet[0] == 'recurrence-events'])
        cntNoRec = len(self.dataSet.loc[self.dataSet[0] == 'no-recurrence-events'])
        return cntRec, cntNoRec

    def getEntropyOneAttribute(self):
        return self.entropy(self.cntRec, self.cntNoRec)

    def getEntropyTwoAttributes(self, attributeId):
        attributesValues = list(set(self.dataSet[attributeId]))
        entropyResult = 0
        for i in attributesValues:
            pc = list(self.dataSet[attributeId]).count(i)
            rec = len(self.dataSet.loc[(self.dataSet[0] == 'recurrence-events') & (self.dataSet[attributeId] == i)])
            noRec = len(
                self.dataSet.loc[(self.dataSet[0] == 'no-recurrence-events') & (self.dataSet[attributeId] == i)])

            entropyResult += (pc / len(self.dataSet)) * self.entropy(rec, noRec)
        return entropyResult

    def informationGain(self, attributeIndex):
        return self.getEntropyOneAttribute() - self.getEntropyTwoAttributes(attributeIndex)

    def chooseBestAtributeFromCurrentSetOfAttributes(self, attributes):
        max = -100000000000000
        currentBest = 0

        for i in attributes:
            curr = self.informationGain(i)
            if (curr > max):
                max = curr
                currentBest = i
        return currentBest

    def isRecMoreCommon(self, node):
        if self.cntRec == self.cntNoRec:
            if node.parent is None:
                return True
            return node.parent.isRec
        return self.cntRec > self.cntNoRec


def classifyExample(decisionTree, testRow):
    node = decisionTree
    if len(node.children) == 0:
        if node.isRec:
            return testRow[0] == 'recurrence-events'
        else:
            return testRow[0] == 'no-recurrence-events'
    res = classifyRec(node, testRow)
    return res


def classifyRec(node, testRow):
    attribute = node.attribute
    value = testRow[attribute]
    for c in node.children:
        if value == c.value:
            if c.isLeaf:
                if c.isRec:
                    return testRow[0] == 'recurrence-events'
                else:
                    return testRow[0] == 'no-recurrence-events'
            else:
                return classifyRec(c, testRow)


K = 60
MIN_SAMPLES_LEAF = 0
MIN_DEPTH = 1


def decisionTreeLearning(examples, attributes, allData, prepruning, depth, parent):
    if prepruning and depth > MIN_DEPTH:
        node = Node()
        node.isLeaf = True
        node.parent = parent
        node.isRec = examples.isRecMoreCommon(node)
        return node
    elif prepruning and len(examples.dataSet) == 0:
        node = Node()
        node.isLeaf = True
        node.parent = parent
        node.isRec = examples.isRecMoreCommon(node)
        return node

    if (not prepruning) and len(examples.dataSet) <= K:
        node = Node()
        node.isLeaf = True
        node.parent = parent
        node.isRec = examples.isRecMoreCommon(node)
        return node
    elif examples.getEntropyOneAttribute() == 0:
        node = Node()
        node.parent = parent
        node.isRec = examples.isRecMoreCommon(node)
        node.isLeaf = True
        return node
    elif len(attributes) == 0:
        node = Node()
        node.parent = parent
        node.isRec = examples.isRecMoreCommon(node)
        node.isLeaf = True
        return node
    else:
        bestAttribute = examples.chooseBestAtributeFromCurrentSetOfAttributes(attributes)
        parent = Node()
        parent.root = bestAttribute
        parent.attribute = bestAttribute
        attributesValues = list(set(allData[bestAttribute]))
        for value in attributesValues:
            newExamplesDataFrame = examples.dataSet.loc[examples.dataSet[bestAttribute] == value]
            newExamples = DataSet(newExamplesDataFrame)
            # if prepruning and len(newExamplesDataFrame) <= MIN_SAMPLES_LEAF:
            #     parent.isLeaf = True
            #     parent.isRec = newExamples.isRecMoreCommon(parent)
            #     break
            newAttributes = []
            for attr in attributes:
                if attr != bestAttribute:
                    newAttributes.append(attr)
            child = decisionTreeLearning(newExamples, newAttributes, allData, prepruning, depth + 1, parent)
            child.value = value
            child.parent = parent
            parent.children.append(child)

        return parent


if __name__ == '__main__':
    df = pd.read_csv("breast-cancer.data", header=None)
    df = df.sample(frac=1).reset_index(drop=True)
    batchSize = int(len(df) / 10)
    meanTrain = 0
    meanTest = 0

    for testIndex in range(10):
        trainData = pd.DataFrame()
        testData = pd.DataFrame()
        for i in range(10):
            if i == testIndex:
                testData = testData.append(df[i * batchSize:i * batchSize + batchSize])
            else:
                trainData = trainData.append(df[i * batchSize:i * batchSize + batchSize])
        trainData = trainData.reset_index(drop=True)
        testData = testData.reset_index(drop=True)
        trainDataSet = DataSet(trainData)
        decisionTree = decisionTreeLearning(trainDataSet, range(1, 10), trainData, False, 0, None)

        sum = 0
        for i, row in testData.iterrows():
            if classifyExample(decisionTree, row):
                sum += 1
        meanTest += sum * 100 / len(testData)
        print("Accuracy: " + (str)(sum * 100 / len(testData)) + "%")

        # Check for overfit
        sum = 0
        for i, row in trainData.iterrows():
            if classifyExample(decisionTree, row):
                sum += 1
        meanTrain += sum * 100 / len(trainData)
        # print(sum * 100 / len(trainData))

    print("The average accuracy on test is: " + (str)(meanTest / 10) + "%")
    print("The average accuracy on train is: " + (str)(meanTrain / 10) + "%")

    # # TEST with small dataset
    # df = pd.read_csv("train.data", header=None)
    # trainDataSet = DataSet(df)
    # decisionTree = decisionTreeLearning(trainDataSet, range(1,5), df, False, 0, None)
    # testData = pd.read_csv("test.data", header=None)
    # sum = 0
    # for i, row in testData.iterrows():
    #     if classifyExample(decisionTree, row) == True:
    #         sum += 1
    # print("The average accuracy on test is: " + (str) (sum * 100 / len(testData)))
    # for i, row in df.iterrows():
    #     if classifyExample(decisionTree, row) == True:
    #         sum += 1
    # print("The average accuracy on train is: " + (str) (sum * 100 / len(df)))
