import numpy as np
import pandas as pd


def updateTables(df, cntRepublicans=0, cntDemocrats=0):
    for index, row in df.iterrows():
        if row[0] == 'democrat':
            cntDemocrats += 1
            isRep = False
        else:
            cntRepublicans += 1
            isRep = True
        for i in range(0,16):
            if isRep:
                if row[i+1] == 'y':
                    freqMatrixRepublicans[0][i] += 1
                elif row[i+1] == 'n':
                    freqMatrixRepublicans[1][i] += 1
                else:
                    freqMatrixRepublicans[2][i] += 1
            else:
                if row[i+1] == 'y':
                    freqMatrixDemocrats[0][i] += 1
                elif row[i+1] == 'n':
                    freqMatrixDemocrats[1][i] += 1
                else:
                    freqMatrixDemocrats[2][i] += 1
    return cntRepublicans, cntDemocrats


def validate(df, countRepublicans, countDemocrats):
    correctDecisions = 0
    for index, row in df.iterrows():
        probabilityRepublican = 0
        probabilityDemocrat = 0
        for j in range(16):
            i = 0
            if row[j+1] == 'n':
                i = 1
            elif row[j+1] == 'y':
                i = 0
            else:
                i = 2
            # calculate probability to be republican
            probabilityRepublican += np.log((freqMatrixRepublicans[i][j] + 1) / (countRepublicans + 3))
            # calculate probability to be democrat
            probabilityDemocrat += np.log((freqMatrixDemocrats[i][j] + 1) / (countDemocrats + 3))

        probabilityRepublican += np.log((countRepublicans + 1) / (countRepublicans + countDemocrats + 2))
        probabilityDemocrat += np.log((countDemocrats + 1) / (countRepublicans + countDemocrats + 2))

        isPredictedRepublican = probabilityRepublican >= probabilityDemocrat
        if isPredictedRepublican:
            if row[0] == 'republican':
                correctDecisions += 1
        else:
            if row[0] == 'democrat':
                correctDecisions += 1

    accuracy = correctDecisions * 100/len(df)
    print("Accuracy: " + str(accuracy) + "%")
    return accuracy


if __name__ == '__main__':
    df = pd.read_csv("house-votes-84.data", header=None)
    batchSize = int(len(df)/10)
    mean = 0
    for testIndex in range(10):
        freqMatrixRepublicans = np.zeros((3, 16))
        freqMatrixDemocrats = np.zeros((3, 16))
        totalCountOfRepublicansInTrainSet = 0
        totalCountOfDemocratsInTrainSet = 0

        for i in range(10):
            if i == testIndex:
                continue
            elif i == 9:
                (cntRepublicans, cntDemocrats) = updateTables(df[i * batchSize:])
                totalCountOfRepublicansInTrainSet += cntRepublicans
                totalCountOfDemocratsInTrainSet += cntDemocrats
            else:
                (cntRepublicans, cntDemocrats) = updateTables(df[i * batchSize:i * batchSize + batchSize])
                totalCountOfRepublicansInTrainSet += cntRepublicans
                totalCountOfDemocratsInTrainSet += cntDemocrats
        mean += validate(df[testIndex * batchSize:testIndex * batchSize + batchSize],
                         totalCountOfRepublicansInTrainSet, totalCountOfDemocratsInTrainSet)
    mean = mean/10
    print("The average accuracy is: " + str(mean) + "%")