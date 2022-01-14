import math

import numpy as np

def sigmoid(x):
    return 1 / (1 + np.exp(-x))

def activation(X, W, bias):
    sum = np.dot(X, W)
    sum += bias
    return sigmoid(sum)


def printResult(expectedOutput):
    output = train(expectedOutput)

    print("0 0: ", end = "")
    print(output[0])
    print("1 0: ", end = "")
    print(output[1])
    print("0 1: ", end = "")
    print(output[2])
    print("1 1: ", end = "")
    print(output[3])


def train(expectedOutput):
    inputLayerNeurons, hiddenLayerNeurons, outputLayerNeurons = 2, 2, 1
    learningRate = 0.1
    epochs = 10000
    input = np.array([[0, 0], [1, 0], [0, 1], [1, 1]])
    WHidden = np.random.uniform(size=(inputLayerNeurons, hiddenLayerNeurons))
    bHidden = np.random.uniform(size=(1, hiddenLayerNeurons))

    WOutput = np.random.uniform(size=(hiddenLayerNeurons, outputLayerNeurons))
    bOutput = np.random.uniform(size=(1, outputLayerNeurons))

    for _ in range(epochs):
        # forward-pass
        hiddenOutput = activation(input, WHidden, bHidden)
        output = activation(hiddenOutput, WOutput, bOutput)

        # Backpropagation
        outputError = output * (1 - output) * (expectedOutput - output)
        hiddenError = hiddenOutput * (1 - hiddenOutput) * np.dot(outputError, WOutput.T)

        # update weights
        WOutput += np.dot(hiddenOutput.T, outputError) * learningRate
        WHidden += np.dot(input.T, hiddenError) * learningRate

        bOutput += np.sum(outputError, axis=0, keepdims=True) * learningRate
        bHidden += np.sum(hiddenError, axis=0, keepdims=True) * learningRate

    return output


if __name__ == '__main__':
    print("OR:")
    printResult(np.array([[0], [1], [1], [1]]))
    print("AND:")
    printResult(np.array([[0], [0], [0], [1]]))
    print("XOR:")
    printResult(np.array([[0], [1], [1], [0]]))


