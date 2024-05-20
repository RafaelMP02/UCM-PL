(module 
(import "runtime" "print" (func $print (param i32)))
(import "runtime" "read" (func $read (result i32)))
(memory 2000)
(global $SP (mut i32) (i32.const 4)) ;; start of stack
(global $MP (mut i32) (i32.const 0)) ;; mark pointer
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
global.get $SP
global.get $SP
i32.const 4
i32.add
global.set $SP
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
i32.const 4
i32.add
global.set $SP
local.get $i
i32.const 4
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
call $print
(return)
)
( func $fun0
(result i32)
 (local $param1 i32)
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
(drop)
local.get $param1
(i32.load)
i32.const 0
i32.eq
if
i32.const 1
(return)
else
local.get $param1
(i32.load)
i32.const 1
i32.eq
if
i32.const 0
(return)
else
global.get $SP
global.set $MP
global.get $SP
i32.const 4
i32.add
global.set $SP
global.get $SP
local.tee $i
i32.const 4
i32.add
global.set $SP
local.get $i
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
(return)
end
end
i32.const 0
(return)
(return)
)
)
