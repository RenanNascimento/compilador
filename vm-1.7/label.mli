
(* les labels sont cr��s pendant le parsing et partag�s *)

type t = { symbolic : string; mutable address : int }

val clear : unit -> unit

val create : string -> t



