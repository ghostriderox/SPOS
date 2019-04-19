gcc -shared -lc -o add.so add.o
gcc run.c -ldl
./a.out
