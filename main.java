import java.util.*;

// Other imports go here

class Node {
    int x, y;
    char value;
    Node next;
    Node prev;

    Node(int x, int y, char value) {
        this.x = x;
        this.y = y;
        this.value = value;
    }
}

class Main {

    static Node head = null, tail = null;
    static char board[][] = new char[20][20];

    public static void main(String[] args) throws Exception {
        System.out.println(
                "Welcome\nThis is a snake game.\nTo quit press Q at any time.\nPress C to continue.\nMove the snake with L - LEFT, R - RIGHT, U - UP & D-DOWN");
        startGame(); // start the snake game.
    }

    private static void startGame() {
        Scanner sc = new Scanner(System.in);
        char ch = sc.next().toLowerCase().charAt(0);
        if (ch == 'c') {
            initSnake(2); // make initial snake of some size.
            String directions = "lrud";
            char dir = sc.next().toLowerCase().charAt(0);
            Game: while (directions.contains(Character.toString(dir))) {
                // check if snake can move in the given direction if moveSnake return 1 continue
                short canMove = moveSnake(dir);
                if (canMove == -1)
                    break Game;
                while (canMove == 0) {
                    System.out.println("Can't move in this direction, enter another direction.");
                    dir = sc.next().toLowerCase().charAt(0);
                    if (dir == 'q')
                        break Game;
                    canMove = moveSnake(dir);
                }
                // allocate snake on the board
                allocateSnake();
                dir = sc.next().toLowerCase().charAt(0);
            }
            System.out.println("Game Over!");
            sc.close();
        }

    }

    private static short moveSnake(char d) {
        // find value of new head
        int x = 0, y = 0;
        char val = ' ';
        if (d == 'l') {
            x = head.x;
            y = head.y - 1;
            val = '<';
        } else if (d == 'r') {
            x = head.x;
            y = head.y + 1;
            val = '>';
        } else if (d == 'u') {
            x = head.x - 1;
            y = head.y;
            val = '^';
        } else if (d == 'd') {
            x = head.x + 1;
            y = head.y;
            val = 'v';
        }
        // check if direction makes snake go in reverse
        if (head.next.x == x && head.next.y == y) {
            allocateSnake();
            return 0;
        }
        // check if snake bites itself
        for (Node n = head.next; n.next != null; n = n.next) {
            if (n.x == x && n.y == y)
                return -1;
        }
        // give snake its new coordinates
        tail = tail.prev;
        tail.next = null;
        tail.value = 'Y';
        Node n = head;
        n.value = 'x';
        head = new Node(x, y, val);
        head.next = n;
        n.prev = head;
        return 1;
    }

    private static void allocateSnake() {
        if (head == null)
            return;
        board = new char[20][20];
        Node n = head;
        while (n != null) {
            if (n != head && n.x == head.x && n.y == head.y) {
                return;
            }
            // print location of snake
            System.out.printf("value %c => %d %d\t", n.value, n.x, n.y);
            board[n.x][n.y] = n.value;
            n = n.next;
        }
        System.out.println();
        printBoard();
        return;
    }

    static void printBoard() {
        for (int i = -1; i <= board.length; i++) {
            for (int j = -1; j <= board.length; j++) {
                if (j == -1 || i == -1 || i == board.length || j == board.length) {
                    System.out.print("*");
                } else {
                    if (board[i][j] == 0) {
                        System.out.print(" ");
                    } else {
                        System.out.print(board[i][j]);
                    }
                }
                System.out.print(" ");
            }
            System.out.println("");
        }
    }

    private static void initSnake(int size) {
        head = new Node(5, 5, '<');
        Node n = head;
        for (int i = 0; i < size; i++) {
            Node nn = new Node(n.x, n.y + 1, 'x');
            n.next = nn;
            nn.prev = n;
            n = nn;
        }
        tail = new Node(n.x, n.y + 1, 'Y');
        n.next = tail;
        tail.prev = n;
        allocateSnake();
    }
}
