(module
(import "runtime" "print" (func $print (param i32)))
(import "runtime" "read" (func $read (result i32)))
(import "runtime" "read" (func $read (type $_sig_ri32)))
(memory 2000)
(global $SP (mut i32) (i32.const 0)) ;; start of stack
(global $MP (mut i32) (i32.const 0)) ;; mark pointer
(global $NP (mut i32) (i32.const 131071996)) ;; heap 2000*64*1024-4
(start $main)
  (func $copy_memory (param $src i32) (param $dest i32) (param $n i32)
    (local $i i32)
    (local.set $i (i32.const 0))
    (block 
      (loop 
        (br_if 1 (i32.ge_u (local.get $i) (local.get $n)))
        (local.get $src)
        (local.get $i)
        (i32.const 4)
        (i32.mul)
        (i32.add)
        (i32.load)
        (local.get $dest)
        (local.get $i)
        (i32.const 4)
        (i32.mul)
        (i32.add)
        (i32.store)
        (local.set $i (i32.add (local.get $i) (i32.const 1)))
        (br 0)
      )
    )
  )
( func $fun0
(result i32)
 (local $param1 i32)
(local $i i32)
(local $temp i32)
(get_global $MP)
(get_global $SP)
(i32.store)
(get_global $SP)
(i32.const 4)
(i32.add)
(get_global $MP)
(i32.const 4)
(i32.add)
(tee_local $i)
(i32.load)
(set_local $param1)
(get_local) $i
(i32.const 4)
(i32.add)
(tee_local) $i
(drop)
get_global $SP
set_global $MP
(return)
)
( func $fun1
(result i32)
 (local $param1 i32)
(local $i i32)
(local $temp i32)
(get_global $MP)
(get_global $SP)
(i32.store)
(get_global $SP)
(i32.const 4)
(i32.add)
(get_global $MP)
(i32.const 4)
(i32.add)
(tee_local $i)
(i32.load)
(set_local $param1)
(get_local) $i
(i32.const 4)
(i32.add)
(tee_local) $i
(drop)
get_global $SP
set_global $MP
(return)
)
( func $main
(result i32)
(local $i i32)
(local $temp i32)
(get_global $MP)
(get_global $SP)
(i32.store)
(get_global $SP)
(i32.const 4)
(i32.add)
(get_global $MP)
(i32.const 4)
(i32.add)
(tee_local $i)
(drop)
get_global $SP
set_global $MP
get_global $SP
i32.const 4
i32.add
set_global $SP
get_global $SP
tee_local $i
i32.const 4
i32.add
nullget_local $i
(i32.store
get_local $i
i32.const 4
i32.add
set_local $i
call $fun0
get_global $MP
set_global $SP
get_global $SP
i32.load
set_global $MP
i32.store
i32.const 12
i32.const 8

 i32.load
i32.store
(return)
)
)
