
struct abc_t {
  int id;
  int num;
};

struct abc_t* f(){
  return g();
}
int main(int argc,char *argv[]) {
  int fd;
  printf("hello\n");
  fd = open("abc", "r");
  close(fd);
  f();
  return 0;
}
