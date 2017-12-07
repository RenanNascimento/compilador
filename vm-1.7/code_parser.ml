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

open Parsing;;
let _ = parse_error;;
# 2 "code_parser.mly"
  open Instr
# 36 "code_parser.ml"
let yytransl_const = [|
  262 (* PUSHI *);
  263 (* PUSHN *);
  264 (* PUSHF *);
  265 (* PUSHS *);
  266 (* PUSHG *);
  267 (* PUSHL *);
  268 (* LOAD *);
  269 (* DUP *);
  270 (* POP *);
  271 (* STOREL *);
  272 (* STOREG *);
  273 (* STORE *);
  274 (* CHECK *);
  275 (* LABEL *);
  276 (* JUMP *);
  277 (* JZ *);
  278 (* PUSHA *);
  279 (* ERR *);
  280 (* ALLOC *);
  281 (* COLON *);
    0 (* EOF *);
  282 (* COMMA *);
    0|]

let yytransl_block = [|
  257 (* IDENT *);
  258 (* INT *);
  259 (* FLOAT *);
  260 (* STRING *);
  261 (* INSTR *);
  283 (* COMMENT *);
    0|]

let yylhs = "\255\255\
\001\000\001\000\001\000\002\000\002\000\004\000\004\000\003\000\
\003\000\005\000\005\000\005\000\005\000\005\000\005\000\005\000\
\005\000\005\000\005\000\005\000\005\000\005\000\005\000\005\000\
\005\000\005\000\005\000\005\000\005\000\000\000"

let yylen = "\002\000\
\001\000\002\000\003\000\002\000\001\000\002\000\001\000\002\000\
\001\000\002\000\001\000\002\000\002\000\002\000\002\000\002\000\
\002\000\002\000\002\000\002\000\002\000\002\000\002\000\004\000\
\002\000\002\000\002\000\002\000\002\000\002\000"

let yydefred = "\000\000\
\000\000\000\000\000\000\011\000\000\000\000\000\000\000\000\000\
\000\000\000\000\000\000\000\000\000\000\000\000\000\000\000\000\
\000\000\000\000\000\000\000\000\000\000\000\000\001\000\000\000\
\030\000\000\000\000\000\000\000\000\000\010\000\012\000\013\000\
\014\000\015\000\016\000\017\000\018\000\019\000\020\000\021\000\
\022\000\023\000\000\000\025\000\026\000\027\000\028\000\029\000\
\008\000\002\000\000\000\004\000\006\000\000\000\003\000\024\000"

let yydgoto = "\002\000\
\025\000\026\000\027\000\028\000\029\000"

let yysindex = "\017\000\
\001\000\000\000\002\255\000\000\022\255\026\255\027\255\025\255\
\030\255\031\255\032\255\033\255\034\255\035\255\036\255\037\255\
\038\255\040\255\041\255\042\255\043\255\044\255\000\000\017\255\
\000\000\045\000\255\254\255\254\017\255\000\000\000\000\000\000\
\000\000\000\000\000\000\000\000\000\000\000\000\000\000\000\000\
\000\000\000\000\024\255\000\000\000\000\000\000\000\000\000\000\
\000\000\000\000\048\000\000\000\000\000\049\255\000\000\000\000"

let yyrindex = "\000\000\
\000\000\000\000\000\000\000\000\000\000\000\000\000\000\000\000\
\000\000\000\000\000\000\000\000\000\000\000\000\000\000\000\000\
\000\000\000\000\000\000\000\000\000\000\000\000\000\000\025\000\
\000\000\000\000\000\000\052\000\049\000\000\000\000\000\000\000\
\000\000\000\000\000\000\000\000\000\000\000\000\000\000\000\000\
\000\000\000\000\000\000\000\000\000\000\000\000\000\000\000\000\
\000\000\000\000\000\000\000\000\000\000\000\000\000\000\000\000"

let yygindex = "\000\000\
\000\000\231\255\002\000\000\000\000\000"

let yytablesize = 329
let yytable = "\003\000\
\023\000\051\000\052\000\004\000\005\000\006\000\007\000\008\000\
\009\000\010\000\011\000\012\000\013\000\014\000\015\000\016\000\
\017\000\001\000\018\000\019\000\020\000\021\000\022\000\031\000\
\009\000\049\000\030\000\032\000\034\000\033\000\053\000\035\000\
\036\000\037\000\038\000\039\000\040\000\041\000\042\000\043\000\
\044\000\045\000\046\000\024\000\050\000\048\000\047\000\055\000\
\007\000\054\000\056\000\005\000\000\000\000\000\000\000\000\000\
\000\000\000\000\000\000\000\000\000\000\000\000\000\000\000\000\
\000\000\000\000\000\000\000\000\000\000\000\000\000\000\000\000\
\000\000\000\000\000\000\000\000\000\000\000\000\000\000\000\000\
\000\000\000\000\000\000\000\000\000\000\000\000\000\000\000\000\
\000\000\000\000\000\000\000\000\000\000\000\000\000\000\000\000\
\000\000\000\000\000\000\000\000\000\000\000\000\000\000\000\000\
\000\000\000\000\000\000\000\000\000\000\000\000\000\000\000\000\
\000\000\000\000\000\000\000\000\000\000\000\000\000\000\000\000\
\000\000\000\000\000\000\000\000\000\000\000\000\000\000\000\000\
\000\000\000\000\000\000\000\000\000\000\000\000\000\000\000\000\
\000\000\000\000\000\000\000\000\000\000\000\000\000\000\000\000\
\000\000\000\000\000\000\000\000\000\000\000\000\000\000\000\000\
\000\000\000\000\000\000\000\000\000\000\000\000\000\000\000\000\
\000\000\000\000\000\000\000\000\000\000\000\000\000\000\000\000\
\000\000\000\000\000\000\000\000\000\000\000\000\000\000\000\000\
\000\000\000\000\000\000\000\000\000\000\000\000\000\000\000\000\
\000\000\000\000\000\000\000\000\000\000\000\000\000\000\000\000\
\000\000\000\000\000\000\000\000\000\000\000\000\000\000\000\000\
\000\000\000\000\000\000\000\000\000\000\000\000\000\000\000\000\
\000\000\000\000\000\000\000\000\000\000\000\000\000\000\000\000\
\000\000\000\000\000\000\000\000\000\000\000\000\000\000\000\000\
\000\000\000\000\000\000\000\000\000\000\000\000\000\000\000\000\
\000\000\000\000\000\000\000\000\000\000\000\000\000\000\000\000\
\000\000\000\000\000\000\000\000\000\000\000\000\000\000\000\000\
\000\000\000\000\000\000\000\000\000\000\000\000\000\000\000\000\
\000\000\003\000\000\000\000\000\000\000\004\000\005\000\006\000\
\007\000\008\000\009\000\010\000\011\000\012\000\013\000\014\000\
\015\000\016\000\017\000\000\000\018\000\019\000\020\000\021\000\
\022\000\009\000\000\000\024\000\000\000\009\000\009\000\009\000\
\009\000\009\000\009\000\009\000\009\000\009\000\009\000\009\000\
\009\000\009\000\009\000\000\000\009\000\009\000\009\000\009\000\
\009\000\007\000\000\000\000\000\000\000\007\000\007\000\007\000\
\007\000\007\000\007\000\007\000\007\000\007\000\007\000\007\000\
\007\000\007\000\007\000\000\000\007\000\007\000\007\000\007\000\
\007\000"

let yycheck = "\001\001\
\000\000\027\000\028\000\005\001\006\001\007\001\008\001\009\001\
\010\001\011\001\012\001\013\001\014\001\015\001\016\001\017\001\
\018\001\001\000\020\001\021\001\022\001\023\001\024\001\002\001\
\000\000\024\000\025\001\002\001\004\001\003\001\029\000\002\001\
\002\001\002\001\002\001\002\001\002\001\002\001\002\001\002\001\
\001\001\001\001\001\001\027\001\000\000\002\001\004\001\000\000\
\000\000\026\001\002\001\000\000\255\255\255\255\255\255\255\255\
\255\255\255\255\255\255\255\255\255\255\255\255\255\255\255\255\
\255\255\255\255\255\255\255\255\255\255\255\255\255\255\255\255\
\255\255\255\255\255\255\255\255\255\255\255\255\255\255\255\255\
\255\255\255\255\255\255\255\255\255\255\255\255\255\255\255\255\
\255\255\255\255\255\255\255\255\255\255\255\255\255\255\255\255\
\255\255\255\255\255\255\255\255\255\255\255\255\255\255\255\255\
\255\255\255\255\255\255\255\255\255\255\255\255\255\255\255\255\
\255\255\255\255\255\255\255\255\255\255\255\255\255\255\255\255\
\255\255\255\255\255\255\255\255\255\255\255\255\255\255\255\255\
\255\255\255\255\255\255\255\255\255\255\255\255\255\255\255\255\
\255\255\255\255\255\255\255\255\255\255\255\255\255\255\255\255\
\255\255\255\255\255\255\255\255\255\255\255\255\255\255\255\255\
\255\255\255\255\255\255\255\255\255\255\255\255\255\255\255\255\
\255\255\255\255\255\255\255\255\255\255\255\255\255\255\255\255\
\255\255\255\255\255\255\255\255\255\255\255\255\255\255\255\255\
\255\255\255\255\255\255\255\255\255\255\255\255\255\255\255\255\
\255\255\255\255\255\255\255\255\255\255\255\255\255\255\255\255\
\255\255\255\255\255\255\255\255\255\255\255\255\255\255\255\255\
\255\255\255\255\255\255\255\255\255\255\255\255\255\255\255\255\
\255\255\255\255\255\255\255\255\255\255\255\255\255\255\255\255\
\255\255\255\255\255\255\255\255\255\255\255\255\255\255\255\255\
\255\255\255\255\255\255\255\255\255\255\255\255\255\255\255\255\
\255\255\255\255\255\255\255\255\255\255\255\255\255\255\255\255\
\255\255\255\255\255\255\255\255\255\255\255\255\255\255\255\255\
\255\255\255\255\255\255\255\255\255\255\255\255\255\255\255\255\
\255\255\001\001\255\255\255\255\255\255\005\001\006\001\007\001\
\008\001\009\001\010\001\011\001\012\001\013\001\014\001\015\001\
\016\001\017\001\018\001\255\255\020\001\021\001\022\001\023\001\
\024\001\001\001\255\255\027\001\255\255\005\001\006\001\007\001\
\008\001\009\001\010\001\011\001\012\001\013\001\014\001\015\001\
\016\001\017\001\018\001\255\255\020\001\021\001\022\001\023\001\
\024\001\001\001\255\255\255\255\255\255\005\001\006\001\007\001\
\008\001\009\001\010\001\011\001\012\001\013\001\014\001\015\001\
\016\001\017\001\018\001\255\255\020\001\021\001\022\001\023\001\
\024\001"

let yynames_const = "\
  PUSHI\000\
  PUSHN\000\
  PUSHF\000\
  PUSHS\000\
  PUSHG\000\
  PUSHL\000\
  LOAD\000\
  DUP\000\
  POP\000\
  STOREL\000\
  STOREG\000\
  STORE\000\
  CHECK\000\
  LABEL\000\
  JUMP\000\
  JZ\000\
  PUSHA\000\
  ERR\000\
  ALLOC\000\
  COLON\000\
  EOF\000\
  COMMA\000\
  "

let yynames_block = "\
  IDENT\000\
  INT\000\
  FLOAT\000\
  STRING\000\
  INSTR\000\
  COMMENT\000\
  "

let yyact = [|
  (fun _ -> failwith "parser")
; (fun __caml_parser_env ->
    Obj.repr(
# 20 "code_parser.mly"
                        ( [] )
# 245 "code_parser.ml"
               : (Instr.t*string option) list))
; (fun __caml_parser_env ->
    let _1 = (Parsing.peek_val __caml_parser_env 1 : 'instrs) in
    Obj.repr(
# 21 "code_parser.mly"
                       ( _1 )
# 252 "code_parser.ml"
               : (Instr.t*string option) list))
; (fun __caml_parser_env ->
    let _1 = (Parsing.peek_val __caml_parser_env 2 : 'comms) in
    let _2 = (Parsing.peek_val __caml_parser_env 1 : 'instrs) in
    Obj.repr(
# 22 "code_parser.mly"
                        ( _2 )
# 260 "code_parser.ml"
               : (Instr.t*string option) list))
; (fun __caml_parser_env ->
    let _1 = (Parsing.peek_val __caml_parser_env 1 : 'instr_com) in
    let _2 = (Parsing.peek_val __caml_parser_env 0 : 'instrs) in
    Obj.repr(
# 26 "code_parser.mly"
                            ( _1 :: _2 )
# 268 "code_parser.ml"
               : 'instrs))
; (fun __caml_parser_env ->
    let _1 = (Parsing.peek_val __caml_parser_env 0 : 'instr_com) in
    Obj.repr(
# 27 "code_parser.mly"
                            ( [ _1 ] )
# 275 "code_parser.ml"
               : 'instrs))
; (fun __caml_parser_env ->
    let _1 = (Parsing.peek_val __caml_parser_env 1 : 'instr) in
    let _2 = (Parsing.peek_val __caml_parser_env 0 : 'comms) in
    Obj.repr(
# 31 "code_parser.mly"
                (_1,Some _2)
# 283 "code_parser.ml"
               : 'instr_com))
; (fun __caml_parser_env ->
    let _1 = (Parsing.peek_val __caml_parser_env 0 : 'instr) in
    Obj.repr(
# 32 "code_parser.mly"
          (_1,None)
# 290 "code_parser.ml"
               : 'instr_com))
; (fun __caml_parser_env ->
    let _1 = (Parsing.peek_val __caml_parser_env 1 : string) in
    let _2 = (Parsing.peek_val __caml_parser_env 0 : 'comms) in
    Obj.repr(
# 36 "code_parser.mly"
                  (_1)
# 298 "code_parser.ml"
               : 'comms))
; (fun __caml_parser_env ->
    let _1 = (Parsing.peek_val __caml_parser_env 0 : string) in
    Obj.repr(
# 37 "code_parser.mly"
                  (_1)
# 305 "code_parser.ml"
               : 'comms))
; (fun __caml_parser_env ->
    let _1 = (Parsing.peek_val __caml_parser_env 1 : string) in
    Obj.repr(
# 40 "code_parser.mly"
                  ( Label (Label.create _1) )
# 312 "code_parser.ml"
               : 'instr))
; (fun __caml_parser_env ->
    let _1 = (Parsing.peek_val __caml_parser_env 0 : Instr.t) in
    Obj.repr(
# 41 "code_parser.mly"
                  ( _1 )
# 319 "code_parser.ml"
               : 'instr))
; (fun __caml_parser_env ->
    let _2 = (Parsing.peek_val __caml_parser_env 0 : int) in
    Obj.repr(
# 42 "code_parser.mly"
                  ( Pushi _2 )
# 326 "code_parser.ml"
               : 'instr))
; (fun __caml_parser_env ->
    let _2 = (Parsing.peek_val __caml_parser_env 0 : int) in
    Obj.repr(
# 43 "code_parser.mly"
                  ( Pushn _2 )
# 333 "code_parser.ml"
               : 'instr))
; (fun __caml_parser_env ->
    let _2 = (Parsing.peek_val __caml_parser_env 0 : float) in
    Obj.repr(
# 44 "code_parser.mly"
                  ( Pushf _2 )
# 340 "code_parser.ml"
               : 'instr))
; (fun __caml_parser_env ->
    let _2 = (Parsing.peek_val __caml_parser_env 0 : string) in
    Obj.repr(
# 45 "code_parser.mly"
                  ( Pushs (Hstring.create _2) )
# 347 "code_parser.ml"
               : 'instr))
; (fun __caml_parser_env ->
    let _2 = (Parsing.peek_val __caml_parser_env 0 : int) in
    Obj.repr(
# 46 "code_parser.mly"
                  ( Pushg _2 )
# 354 "code_parser.ml"
               : 'instr))
; (fun __caml_parser_env ->
    let _2 = (Parsing.peek_val __caml_parser_env 0 : int) in
    Obj.repr(
# 47 "code_parser.mly"
                  ( Pushl _2 )
# 361 "code_parser.ml"
               : 'instr))
; (fun __caml_parser_env ->
    let _2 = (Parsing.peek_val __caml_parser_env 0 : int) in
    Obj.repr(
# 48 "code_parser.mly"
                  ( Load _2 )
# 368 "code_parser.ml"
               : 'instr))
; (fun __caml_parser_env ->
    let _2 = (Parsing.peek_val __caml_parser_env 0 : int) in
    Obj.repr(
# 49 "code_parser.mly"
                  ( Dup _2 )
# 375 "code_parser.ml"
               : 'instr))
; (fun __caml_parser_env ->
    let _2 = (Parsing.peek_val __caml_parser_env 0 : int) in
    Obj.repr(
# 50 "code_parser.mly"
                  ( Pop _2 )
# 382 "code_parser.ml"
               : 'instr))
; (fun __caml_parser_env ->
    let _2 = (Parsing.peek_val __caml_parser_env 0 : int) in
    Obj.repr(
# 51 "code_parser.mly"
                  ( Storel _2 )
# 389 "code_parser.ml"
               : 'instr))
; (fun __caml_parser_env ->
    let _2 = (Parsing.peek_val __caml_parser_env 0 : int) in
    Obj.repr(
# 52 "code_parser.mly"
                  ( Storeg _2 )
# 396 "code_parser.ml"
               : 'instr))
; (fun __caml_parser_env ->
    let _2 = (Parsing.peek_val __caml_parser_env 0 : int) in
    Obj.repr(
# 53 "code_parser.mly"
                  ( Store _2 )
# 403 "code_parser.ml"
               : 'instr))
; (fun __caml_parser_env ->
    let _2 = (Parsing.peek_val __caml_parser_env 2 : int) in
    let _4 = (Parsing.peek_val __caml_parser_env 0 : int) in
    Obj.repr(
# 54 "code_parser.mly"
                        ( Check (_2, _4) )
# 411 "code_parser.ml"
               : 'instr))
; (fun __caml_parser_env ->
    let _2 = (Parsing.peek_val __caml_parser_env 0 : string) in
    Obj.repr(
# 55 "code_parser.mly"
                  ( Jump (Label.create _2) )
# 418 "code_parser.ml"
               : 'instr))
; (fun __caml_parser_env ->
    let _2 = (Parsing.peek_val __caml_parser_env 0 : string) in
    Obj.repr(
# 56 "code_parser.mly"
                  ( Jz (Label.create _2) )
# 425 "code_parser.ml"
               : 'instr))
; (fun __caml_parser_env ->
    let _2 = (Parsing.peek_val __caml_parser_env 0 : string) in
    Obj.repr(
# 57 "code_parser.mly"
                  ( Pusha (Label.create _2) )
# 432 "code_parser.ml"
               : 'instr))
; (fun __caml_parser_env ->
    let _2 = (Parsing.peek_val __caml_parser_env 0 : string) in
    Obj.repr(
# 58 "code_parser.mly"
                  ( Err _2 )
# 439 "code_parser.ml"
               : 'instr))
; (fun __caml_parser_env ->
    let _2 = (Parsing.peek_val __caml_parser_env 0 : int) in
    Obj.repr(
# 59 "code_parser.mly"
                  ( Alloc _2 )
# 446 "code_parser.ml"
               : 'instr))
(* Entry main *)
; (fun __caml_parser_env -> raise (Parsing.YYexit (Parsing.peek_val __caml_parser_env 0)))
|]
let yytables =
  { Parsing.actions=yyact;
    Parsing.transl_const=yytransl_const;
    Parsing.transl_block=yytransl_block;
    Parsing.lhs=yylhs;
    Parsing.len=yylen;
    Parsing.defred=yydefred;
    Parsing.dgoto=yydgoto;
    Parsing.sindex=yysindex;
    Parsing.rindex=yyrindex;
    Parsing.gindex=yygindex;
    Parsing.tablesize=yytablesize;
    Parsing.table=yytable;
    Parsing.check=yycheck;
    Parsing.error_function=parse_error;
    Parsing.names_const=yynames_const;
    Parsing.names_block=yynames_block }
let main (lexfun : Lexing.lexbuf -> token) (lexbuf : Lexing.lexbuf) =
   (Parsing.yyparse yytables 1 lexfun lexbuf : (Instr.t*string option) list)
