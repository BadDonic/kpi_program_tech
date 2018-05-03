Scenario: Entering and saving a variable
Given Compiler
When I compile string <input>
Then I should get result <result>
Then should be created var <varname> with value <result>

Examples:
|input                          |varname                |result                              |
|A = 100                        |A                      |100.0                               |
|B = [[0.32, -1.2, 32]]         |B                      |[[0.32, -1.2, 32.0]]                |
|C = [[-10.32, 0], [1.2, 32]]   |C                      |[[-10.32, 0.0], [1.2, 32.0]]        |


Scenario: Entering a variable with invalid value
Given Compiler
When I compile string <input>
Then I should get result <error>

Examples:
|input                           |error                                                         |
|A = 1dasdsa                     |Invalid input: 1dasdsa                                        |
|B = [0.32, -1.2, 32]]           |Invalid input Matrix: [0.32,-1.2,32]]                         |
|C = [[-10.32, 0], [1.2, 32]     |Invalid input Matrix: [[-10.32,0],[1.2,32]                    |

Scenario: Entering a variable with invalid name
Given Compiler
When I compile string <input>
Then I should get result <error>

Examples:
|input                             |error                                                        |
|21A = 1                           |Invalid input before equals: 21A                             |
|#21 = [0.32, -1.2, 32]            |Invalid input before equals: #21                             |
|$e2 = [[-10.32, 0], [1.2, 32]]    |Invalid input before equals: $e2                             |

Scenario: Querying saved variable
Given Compiler with a created variable <varname> with value <value>
When I compile string <varname>
Then I should get result <result>

Examples:
|varname             |value                              |result                          |
|A                   |-10.32                             |-10.32                          |
|B                   |[[-10.32, 1.2, 32]]                |[[-10.32, 1.2, 32.0]]           |
|C                   |[[-10.32, 0], [1.2, 32]]           |[[-10.32, 0.0], [1.20, 32.0]]   |

Scenario: Querying a variable that is not saved
Given Compiler
When I compile string <input>
Then I should get result <error>

Examples:
|input                  |error                             |
|A                      |Does not exist variable - A       |
|B                      |Does not exist variable - B       |
|C                      |Does not exist variable - C       |

Scenario: Overriding variable
Given Compiler with a created variable <varname> with value <value>
When I compile string <input>
Then I should get result <result>
Then should be created var <varname> with value <result>

Examples:
|varname |value                              |input                      |result                         |
|A       |-10.32                             |A = [[1111, 0.32, 1222]]   |[[1111.0, 0.32, 1222.0]]       |
|B       |[[-10.32, 1.2, 32]]                |B = 1002                   |1002.0                         |
|C       |[[-10.32, 0], [1.2, 32]]           |C = [[111, 5], [1.2, -3]]  |[[111.0, 5.0], [1.20, -3.0]]   |

Scenario: Saving an expression
Given Compiler
When I compile string <input>
Then I should get result <result>
Then should be created var <varname> with value <result>

Examples:
|input                                         |varname    |result                                  |
|A = 20 / 10 - 12 - 3                          |A          |-13.0                                   |
|B = [[0.23, 1.12, 32] - [-0.21, 1.2, 20]]     |B          |[[0.02, 2.32, 52.0]]                    |
|C = [[-10, 0], [14, 1]] - [[5, 0], [2.2, 1]]  |C          |[[-5.0, 0.0], [16.2, 2.0]]              |
