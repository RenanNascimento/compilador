START
PUSHN 6
PUSHS "Nome: "
WRITES
READ
STOREL 3
PUSHS "Sobrenome: "
WRITES
READ
STOREL 4
PUSHS "Ola, "
PUSHG 3
SWAP
CONCAT
PUSHS " "
SWAP
CONCAT
PUSHG 4
SWAP
CONCAT
PUSHS "!"
SWAP
CONCAT
STOREL 5
PUSHG 5
PUSHS "um"
SWAP
CONCAT
STOREL 5
PUSHG 5
WRITES
READ
ATOI
STOREL 0
READ
ATOI
STOREL 2
PUSHG 0
PUSHG 2
SUP
JZ A
PUSHG 2
STOREL 1
PUSHG 0
STOREL 2
PUSHG 1
STOREL 0
A:
PUSHS "Apos a troca: "
WRITES
PUSHG 0
WRITEI
PUSHG 2
WRITEI
STOP