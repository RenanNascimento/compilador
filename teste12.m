START
PUSHN 4
PUSHI 1
STOREL 0
PUSHI 2
STOREL 1
PUSHI 3
STOREL 2
PUSHI 4
STOREL 3
PUSHG 2
PUSHG 0
EQUAL
PUSHG 2
PUSHG 1
EQUAL
ADD
JZ A
PUSHS "Valor c: "
WRITES
PUSHG 2
WRITEI
PUSHS "\n"
WRITES
JUMP B
A:
PUSHG 2
PUSHG 3
INFEQ
JZ C
PUSHS "Valor d:"
WRITES
PUSHG 3
WRITEI
PUSHS "\n"
WRITES
C:
B:
PUSHG 2
PUSHG 3
SUB
PUSHG 0
EQUAL
NOT
JZ E
PUSHS "Valor a:"
WRITES
PUSHG 0
WRITEI
E:
STOP
