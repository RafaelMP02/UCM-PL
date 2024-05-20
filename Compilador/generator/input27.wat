(module 
(import "runtime" "print" (func $print (param i32)))
(import "runtime" "read" (func $read (result i32)))
(memory 2000)
(global $SP (mut i32) (i32.const 8)) ;; start of stack
(global $MP (mut i32) (i32.const 4)) ;; mark pointer
(global $NP (mut i32) (i32.const 131071996)) ;; heap 2000*64*1024-4
(start $main)
  (func $copy_memory (param $src i32) (param $dest i32) (param $n i32)
    (local $i i32)
    (local.set $i (i32.const 0))
    (block 
      (loop 
        (br_if 1 (i32.ge_u (local.get $i) (local.get $n)))
        (local.get $dest)
        (local.get $i)
        (i32.const 4)
        (i32.mul)
        (i32.add)
        (local.get $src)
        (local.get $i)
        (i32.const 4)
        (i32.mul)
        (i32.add)
        (i32.load)
        (i32.store)
        (local.set $i (i32.add (local.get $i) (i32.const 1)))
        (br 0)
      )
    )
  )
( func $main
(local $i i32)
(local $temp i32)
(global.get $MP)
(i32.const 4)
(i32.add)
(local.tee $i)
(drop)
i32.const 3
i32.store
i32.const 2
i32.store
global.get $SP
global.set $MP
global.get $SP
i32.const 4
i32.add
global.set $SP
global.get $SP
local.tee $i
i32.const 8
i32.add
global.set $SP
local.get $i
i32.const 8
(i32.store)
local.get $i
i32.const 4
i32.add
local.set $i
local.get $i
i32.const 12
(i32.store)
local.get $i
i32.const 4
i32.add
local.set $i
call $fun1
global.get $MP
global.set $SP
global.get $SP
i32.load
global.set $MP
call $print
(return)
)
( func $fun0
(result i32)
 (local $param1 i32)
 (local $param2 i32)
(local $i i32)
(local $temp i32)
(global.get $MP)
(i32.const 4)
(i32.add)
(local.tee $i)
(i32.load)
(local.set $param1)
(local.get $i)
(i32.const 4)
(i32.add)
(local.tee $i)
(i32.load)
(local.set $param2)
(local.get $i)
(i32.const 4)
(i32.add)
(local.tee $i)
(drop)
local.get $param1
(i32.load)
i32.const 0
i32.eq
if
global.get $SP
global.set $MP
i32.const 0
(return)
global.get $MP
global.set $SP
global.get $SP
i32.load
global.set $MP
else
local.get $param1
(i32.load)
i32.const 1
i32.eq
if
global.get $SP
global.set $MP
local.get $param2
(i32.load)
(return)
global.get $MP
global.set $SP
global.get $SP
i32.load
global.set $MP
else
global.get $SP
global.set $MP
local.get $param2
(i32.load)
global.get $SP
global.set $MP
global.get $SP
i32.const 4
i32.add
global.set $SP
global.get $SP
local.tee $i
i32.const 8
i32.add
global.set $SP
local.get $i
local.get $param2
(i32.store)
local.get $i
i32.const 4
i32.add
local.set $i
local.get $i
(i32.store)
local.get $i
i32.const 4
i32.add
local.set $i
call $fun1
global.get $MP
global.set $SP
global.get $SP
i32.load
global.set $MP
i32.add
(return)
global.get $MP
global.set $SP
global.get $SP
i32.load
global.set $MP
end
end
(return)
)
( func $fun1
(result i32)
 (local $param1 i32)
 (local $param2 i32)
(local $i i32)
(local $temp i32)
(global.get $MP)
(i32.const 4)
(i32.add)
(local.tee $i)
(i32.load)
(local.set $param1)
(local.get $i)
(i32.const 4)
(i32.add)
(local.tee $i)
(i32.load)
(local.set $param2)
(local.get $i)
(i32.const 4)
(i32.add)
(local.tee $i)
(drop)
( func $fun2
(result i32)
 (local $param1 i32)
 (local $param2 i32)
(local $i i32)
(local $temp i32)
(global.get $MP)
(i32.const 4)
(i32.add)
(local.tee $i)
(i32.load)
(local.set $param1)
(local.get $i)
(i32.const 4)
(i32.add)
(local.tee $i)
(i32.load)
(local.set $param2)
(local.get $i)
(i32.const 4)
(i32.add)
(local.tee $i)
(drop)
i32.const 0
i32.store
block 
 loop
local.get $param2
(i32.load)
i32.const 0
i32.gt
i32.eqz 
br_if 1
global.get $SP
global.set $MP
local.get $param2
local.get $param2
(i32.load)
i32.const 1
i32.sub
i32.store
i32.const 8
i32.const 8
(i32.load)
i32.const 1
i32.add
i32.store
global.get $MP
global.set $SP
global.get $SP
i32.load
global.set $MP
br 0
 end
i32.const 0
i32.store
block 
 loop
i32.const 8
(i32.load)
local.get $param1
(i32.load)
i32.lt
i32.eqz 
br_if 1
global.get $SP
global.set $MP
i32.const 8
i32.const 8
(i32.load)
i32.const 1
i32.add
i32.store
global.get $MP
global.set $SP
global.get $SP
i32.load
global.set $MP
i32.const 8
i32.const 8
(i32.load)
i32.const 1
i32.add
i32.store
br 0
 end
(return)
)
local.get null
(i32.load)
i32.const 0
i32.eq
if
global.get $SP
global.set $MP
i32.const 0
(return)
global.get $MP
global.set $SP
global.get $SP
i32.load
global.set $MP
else
local.get null
(i32.load)
i32.const 1
i32.eq
if
global.get $SP
global.set $MP
local.get null
(i32.load)
(return)
global.get $MP
global.set $SP
global.get $SP
i32.load
global.set $MP
else
global.get $SP
global.set $MP
global.get $SP
global.set $MP
global.get $SP
i32.const 4
i32.add
global.set $SP
global.get $SP
local.tee $i
i32.const 8
i32.add
global.set $SP
local.get $i
global.get $SP
i32.store
local.get null
 (global.get $SP)
(i32.const 4)
call $copy_memory 
global.get $SP
i32.const 4
i32.add
global.set $SP
local.get $i
i32.const 4
i32.add
local.set $i
local.get $i
global.get $SP
i32.store
global.get $SP
global.get $SP
global.set $MP
global.get $SP
i32.const 4
i32.add
global.set $SP
global.get $SP
local.tee $i
i32.const 8
i32.add
global.set $SP
local.get $i
(i32.store)
local.get $i
i32.const 4
i32.add
local.set $i
local.get $i
local.get null
(i32.store)
local.get $i
i32.const 4
i32.add
local.set $i
call $fun0
global.get $MP
global.set $SP
global.get $SP
i32.load
global.set $MP
(i32.store
global.get $SP
i32.const 4
i32.add
global.set $SP
local.get $i
i32.const 4
i32.add
local.set $i
call $fun2
global.get $MP
global.set $SP
global.get $SP
i32.load
global.set $MP
(return)
global.get $MP
global.set $SP
global.get $SP
i32.load
global.set $MP
end
end
(return)
)
)
