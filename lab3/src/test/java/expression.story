Scenario: Expression without saving
Given a new compiler
When I compile string <input>
Then I should get result <result>

Examples:
|input                                                            |result                              |
|112.5 - 100                                                      |12.5                                |
|[[1, 2], [2, 1]] - [[1, 2], [2, 1]]                              |[[0.0, 0.0], [0.0, 0.0]]            |
|det([[2, 4], [4, 2]])                                            |-12.0                              |
|([[6, 6, 6]] - [[3, 3, 3]]) / (3 - det([[1, 2], [2, 1]] - [[1, 2], [2, 1]])) |[[1, 1, 1]]                              |

Scenario: Expression with saving variables
Given a new compiler
When I compile string <input>
When I compile string <varname>
Then I should get result <result>

Examples:
|input                                                                |varname           |result                         |
|A = 112.5 - 100                                                      |A                 |12.5                           |
|D = [[1, 2], [2, 1]] - [[1, 2], [2, 1]]                              |D                 |[[0.0, 0.0], [0.0, 0.0]]   |
|E = det([[2, 4], [4, 2]])                                            |E                 |-12.0                         |
|F = ([[6, 6, 6]] - [[3, 3, 3]]) / (3 - det([[1, 2], [2, 1]] - [[1, 2], [2, 1]]))  |F    |[[1.0, 1.0, 1.0]]             |

Scenario: Invalid format
Given a new compiler
When I compile string <input>
Then I should get an error message invalid input

Examples:
|input                                                            |
|100.00 + 112.5A                                                  |
|([2, -2, 1] + [1, 5, 2 ) * [1, 2, 3]                             |
|3 + [1, 5, 2) * [1, 2, 3]                                        |
|[[1, 2], 2, 1]] + [[1, 2], [2, 1]]                               |
|det([[[2, 4], [4, 2]])                                           |
|[1, 5, 2] * [1, 2, 3] + det([[1, 2 [2, 1]] + [[1, 2], [2, 1]])   |

Scenario: Querying saved values
Given a new compiler
When I compile string <input1>
When I compile string <input2>
When I compile string <input3>
Then I should get result <result>

Examples:
|input1                   |input2                |input3                |result                       |
|A = 100.00               |B = -25               |A - B                 |75.0                        |
|A = [2, -2, 1]           |B = [1, 5, 2]         |(A - B)               |[[1.0, -7.0, -1.0]]                        |
|A = [[1, 2], [2, 1]]     |B = [[1, 2], [2, 1]]  |A - B                 |[[0.0, 0.0], [0.0, 0.0]] |

Scenario: Querying saved values and saving new variable
Given a new compiler
When I compile string <input1>
When I compile string <input2>
When I compile string <input3>
Then I should get result <result>

Examples:
|input1                   |input2                |input3                |result                       |
|A = 100.00               |B = -25               |A - B                 |75.0                        |
|A = [2, -2, 1]           |B = [1, 5, 2]         |(A - B)               |[[1.0, -7.0, -1.0]]                        |
|A = [[1, 2], [2, 1]]     |B = [[1, 2], [2, 1]]  |A - B                 |[[0.0, 0.0], [0.0, 0.0]] |

Scenario: Querying a variable that is not saved
Given a new compiler
When I compile string <input>
Then I should get an error message no such variable

Examples:
|input  |
|A - B  |
|det(A) |
|D / E  |
