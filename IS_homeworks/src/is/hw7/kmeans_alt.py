import sys

import matplotlib.pyplot as plt
import numpy
import numpy as np
import pandas as pd

from sklearn.preprocessing import StandardScaler

K = 8


def initRandomCentroids(X, k):
    random_idx = np.random.permutation(X.shape[0])
    means = X[random_idx[:k]]
    return means


def choose(p):
    sumAll = np.sum(p)
    random = np.random.random()
    r = 0.0
    for idx in range(len(p)):
        r += p[idx]
        if r/sumAll > random:
            return idx


def initCentriodsWithMaximumDistance(X, k):
    means = [X[np.random.randint(X.shape[0]), :]]
    for cluster_num in range(k-1):
        dist = []
        for i in range(X.shape[0]):
            point = X[i]
            d = sys.maxsize

            for j in range(len(means)):
                currDist = calculateDistance(point, means[j])
                d = min(d, currDist)
            dist.append(d)

        dist = np.array(dist)

        nextIndex = choose(dist)
        nextMean = X[nextIndex, :]
        means.append(nextMean)
    return means


def calculateDistance(x, centroid):
    return np.sum((x-centroid)**2)


def selectBestCluster(x, means):
    k = len(means)
    minDistance = sys.maxsize
    bestClusterIndex = 0
    for i in range(k):
        curr = calculateDistance(x, means[i])
        if curr < minDistance:
            minDistance = curr
            bestClusterIndex = i
    return minDistance, bestClusterIndex


def sumOfAllPointsInTheCluster(W):
    numOfAllPoints = len(W)
    sum = np.zeros(2)
    for i in range(numOfAllPoints):
        sum[0] += W[i][0]
        sum[1] += W[i][1]
    return sum


def shouldStop(oldMeans, newMeans):
    if len(oldMeans) != len(newMeans):
        return False
    for i in range(len(newMeans)):
        if oldMeans[i][0] != newMeans[i][0] or oldMeans[i][1] != newMeans[i][1]:
            return False
    return True


def kMeans(X, k):
    means = initCentriodsWithMaximumDistance(X, k)
    # means = initRandomCentroids(X, k)
    initMeans = means.copy()
    numOfPoints = X.shape[0]
    labels = np.zeros(X.shape[0])
    while True:
        oldMeans = means.copy()
        for i in range(numOfPoints):
            _, clusterIndex = selectBestCluster(X[i], means)
            labels[i] = clusterIndex
        for clusterId in range(k):
            means[clusterId] = (1 / len(X[labels == clusterId])) * sumOfAllPointsInTheCluster(X[labels == clusterId])
        if shouldStop(oldMeans, means):
            break
    return means, labels, initMeans


if __name__ == '__main__':
    df = pd.read_csv("unbalance.txt", header=None, sep=' ')
    # df = pd.read_csv("normal.txt", header=None, sep='\t')

    X = df.to_numpy()
    means, labels, initMeans = kMeans(X, K)
    colors = ['red', 'green', 'blue', 'yellow', 'orange', 'black', 'pink', 'purple']

    pltMeans = numpy.array(means)
    initMeans = numpy.array(initMeans)

    for i in range(K):
        plt.scatter(X[labels == i, 0], X[labels == i, 1], marker='o', color=colors[i])
    plt.scatter(pltMeans[:,0], pltMeans[:,1], marker='x')
    plt.scatter(initMeans[:,0], initMeans[:,1], marker='v')

    plt.show()

