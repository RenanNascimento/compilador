type token =
  | IDENT of (string)
  | INT of (int)
  | FLOAT of (float)
  | STRING of (string)
  | INSTR of (Instr.t)
  | PUSHI
  | PUSHN
  | PUSHF
  | PUSHS
  | PUSHG
  | PUSHL
  | LOAD
  | DUP
  | POP
  | STOREL
  | STOREG
  | STORE
  | CHECK
  | LABEL
  | JUMP
  | JZ
  | PUSHA
  | ERR
  | ALLOC
  | COLON
  | EOF
  | COMMA
  | COMMENT of (string)

val main :
  (Lexing.lexbuf  -> token) -> Lexing.lexbuf -> (Instr.t*string option) list
