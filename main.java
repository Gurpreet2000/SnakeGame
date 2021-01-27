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
        // Write your code here
        startGame();
        // System.out.println("Welcome\nThis is a snake game.\nTo quit press Q at any
        // time.\nPress C to continue\n");
        // Scanner sc = new Scanner(System.in);
        // char ch = sc.nextLine().charAt(0);
        // sc.close();
        // if (ch == 'C') {
        // startGame();
        // }
        // return;
    }

    private static void startGame() {
        initSnake();
        allocateSnake();
        printBoard();
    }

    private static void initSnake() {
        head = new Node(5, 5, '<');
        Node n = head;
        for (int i = 0; i < 2; i++) {
            Node nn = new Node(n.x, n.y + 1, '-');
            n.next = nn;
            nn.prev = n;
            n = nn;
        }
        tail = new Node(n.x, n.y + 1, '|');
        n.next = tail;
        tail.prev = n;
    }

    private static void allocateSnake() {
        Node n = head;
        if (n == null)
            return;
        while (n != null) {
            board[n.x][n.y] = n.value;
            n = n.next;
        }

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
}
