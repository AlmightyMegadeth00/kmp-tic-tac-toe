package org.kmptictactoe.project

fun List<List<Int>>.hasCompleteIntersect(evaluateSet: Set<Int>): Boolean {
    var hasCompleteIntersect = false
    for (list in this) {
        if (evaluateSet.containsAll(list)) hasCompleteIntersect = true
    }
    return hasCompleteIntersect
}