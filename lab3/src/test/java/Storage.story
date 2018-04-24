Scenario: Entering and saving a variable
Given a new evaluator
When I evaluate string <input>
Then I should get result <result>
Then should be created var <varname> with value <result>

Examples:
|input                          |varname|result                              |
|A = 100                        |A      |100.00                              |
|B = [0.32, -1.2, 32]           |B      |[0.32, -1.20, 32.00]                |
|C = [[-10.32, 0], [1.2, 32]]   |C      |[[-10.32, 0.00], [1.20, 32.00]]     |

Scenario: Entering a variable with invalid value
Given a new evaluator
When I evaluate string <input>
Then I should get an error message invalid input

Examples:
|input                           |
|A = 1dasdsa                     |
|B = [0.32, -1.2, 32]]           |
|C = [[-10.32, 0], [1.2, 32]     |

Scenario: Entering a variable with invalid name
Given a new evaluator
When I evaluate string <input>
Then I should get an error message invalid input

Examples:
|input                             |
|21A = 1                           |
|#21 = [0.32, -1.2, 32]            |
|$e2 = [[-10.32, 0], [1.2, 32]]    |

Scenario: Querying saved variable
Given an evaluator with a created variable <varname> with value <value>
When I evaluate string <varname>
Then I should get result <result>

Examples:
|varname|value                              |result                          |
|A      |-10.32                             |-10.32                          |
|B      |[-10.32, 1.2, 32]                  |[-10.32, 1.20, 32.00]           |
|C      |[[-10.32, 0], [1.2, 32]]           |[[-10.32, 0.00], [1.20, 32.00]] |

Scenario: Querying a variable that is not saved
Given a new evaluator
When I evaluate string <input>
Then I should get an error message no such variable

Examples:
|input  |
|A      |
|B      |
|C      |

Scenario: Overriding variable
Given an evaluator with a created variable <varname> with value <value>
When I evaluate string <input>
Then I should get result <result>
Then should be created var <varname> with value <result>

Examples:
|varname |value                              |input                      |result                           |
|A       |-10.32                             |A = [1111, 0.32, 1222]     |[1111.00, 0.32, 1222.00]         |
|B       |[-10.32, 1.2, 32]                  |B = 1002                   |1002.00                          |
|C       |[[-10.32, 0], [1.2, 32]]           |C = [[111, 5], [1.2, -3]]  |[[111.00, 5.00], [1.20, -3.00]]  |

Scenario: Overriding variable
Given a new evaluator
When I evaluate string <input>
Then I should get result <result>
Then should be created var <varname> with value <result>

Examples:
|varname |value                              |input                      |result                           |
|A       |-10.32                             |A = [1111, 0.32, 1222]     |[1111.00, 0.32, 1222.00]         |
|B       |[-10.32, 1.2, 32]                  |B = 1002                   |1002.00                          |
|C       |[[-10.32, 0], [1.2, 32]]           |C = [[111, 5], [1.2, -3]]  |[[111.00, 5.00], [1.20, -3.00]]  |

Scenario: Saving an expression
Given a new evaluator
When I evaluate string <input>
Then I should get result <result>
Then should be created var <varname> with value <result>

Examples:
|input                                         |varname    |result                                  |
|A = 10 + 42 + 20                              |A          |72.00                                   |
|B = [0.23, 1.12, 32] + [-0.21, 1.2, 20]       |B          |[0.02, 2.32, 52.00]                     |
|C = [[-10, 0], [14, 1]] + [[5, 0], [2.2, 1]]  |C          |[[-5.00, 0.00], [16.20, 2.00]]          |
