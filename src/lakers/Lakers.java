package Lakers;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

public class Lakers extends JFrame implements KeyListener, ActionListener {

    // Player
    private int p = 45, p1 = 45;
    private final int playerSize = 30;
    private final int playerSpeed = 10;
    private final Image playerImage;
    private Image enemyImage;
   
    // Enemy by jurennnjnsjvnsnv
    private int ex, ey;
    private final int enemySize = 30;
    private final int enemySpeed = 5;

    private boolean l, r, u, d;
    private boolean won = false;
    private boolean gameOver = false;

    private int level = 1;

    private final int TILE = 40;
    private final List<Rectangle> mazeWalls = new ArrayList<>();

    // Finish lines
    private final Rectangle finishLevel1 =
            new Rectangle(4 * TILE, 11 * TILE, TILE, TILE);

    private final Rectangle finishLevel2 =
            new Rectangle(18 * TILE, 15 * TILE, TILE, TILE);

    private final int rows = 17; 
    private final int cols = 34; 
    private int[][] maze;
    private final int[][] mazeLevel1 = {
    {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
    {1,0,0,0,1,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
    {1,0,1,0,1,0,1,1,1,0,1,0,1,1,1,1,1,1,0,1,1,1,1,1,1,0,1,1,1,0,1,0,1,1},
    {1,0,1,0,0,0,0,0,1,0,0,0,1,0,0,0,0,1,0,0,0,0,0,0,1,0,0,0,1,0,1,0,0,1},
    {1,1,1,1,1,1,1,0,1,1,1,0,1,1,0,1,0,1,0,1,0,1,0,0,1,0,1,1,1,0,1,0,0,1},
    {1,0,0,0,0,0,1,0,0,0,1,0,0,0,0,1,0,1,0,1,0,1,1,0,1,0,1,0,0,0,1,1,0,1},
    {1,1,1,1,1,0,1,1,1,0,1,1,1,0,0,0,0,0,0,1,0,0,1,0,1,0,1,1,0,1,1,0,0,1},
    {1,0,1,0,1,0,0,0,0,0,0,0,1,1,1,1,0,1,0,0,0,0,1,0,0,0,0,1,0,0,0,0,0,1},
    {1,0,1,0,1,1,0,1,1,1,1,0,1,0,0,1,0,0,0,1,0,0,1,0,1,1,0,1,0,1,1,1,0,1},
    {1,0,0,0,0,0,0,0,1,0,1,0,1,0,0,1,0,1,0,1,1,0,1,1,1,0,0,1,0,1,0,1,0,1},
    {1,1,0,1,1,1,1,1,1,0,1,1,1,0,0,1,1,1,0,0,0,0,0,0,0,0,1,1,0,0,0,1,0,1},
    {1,0,0,1,0,0,0,0,1,0,0,0,0,0,0,0,0,1,1,1,0,1,1,1,0,1,1,0,0,1,0,1,0,1},
    {1,0,1,1,0,1,1,0,1,1,1,1,1,1,0,1,0,0,0,1,0,0,1,0,0,1,0,0,1,1,0,1,0,1},
    {1,0,0,1,0,0,1,0,0,0,0,0,1,0,0,1,0,1,0,1,0,0,1,0,1,1,0,1,1,0,0,1,0,1},
    {1,0,0,1,1,0,1,0,0,1,1,0,1,1,1,1,0,1,0,1,1,1,1,0,0,1,0,1,0,0,0,1,0,1},
    {1,0,0,0,0,0,1,0,0,1,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,1},
    {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
    };

    private final int[][] mazeLevel2 = {
    {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
    {1,0,0,0,1,0,1,0,0,0,1,0,0,0,0,0,1,0,0,0,1,0,0,0,1,0,0,0,1,0,0,0,0,1},
    {1,0,1,0,1,0,1,0,1,1,1,0,1,0,1,1,1,0,1,0,1,0,1,1,1,0,1,0,1,1,1,0,0,1},
    {1,0,1,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
    {1,1,1,1,1,0,1,1,1,0,1,1,1,1,0,1,0,1,1,1,0,1,1,0,1,1,0,1,1,0,1,1,1,1},
    {1,0,0,0,0,0,1,0,0,0,1,0,0,0,0,1,0,0,0,1,0,0,0,0,1,0,0,0,0,0,1,0,0,1},
    {1,0,1,0,1,1,1,1,1,1,1,1,1,1,0,1,1,1,0,1,0,0,0,0,0,1,1,1,0,1,1,0,1,1},
    {1,0,1,0,0,1,0,1,0,0,0,0,0,1,0,0,0,1,0,1,0,0,0,1,0,0,0,1,0,1,0,0,0,1},
    {1,0,1,0,0,0,0,1,0,1,1,1,0,1,0,1,0,1,0,1,0,0,0,1,0,0,0,1,0,0,0,0,0,1},
    {1,0,1,1,1,1,1,1,0,1,0,1,0,1,0,1,0,1,0,1,0,0,1,1,0,0,0,1,0,0,1,1,0,1},
    {1,0,0,0,1,0,0,0,0,0,0,1,0,1,0,1,0,1,0,0,0,0,0,1,0,0,0,1,0,0,0,0,0,1},
    {1,1,1,1,1,1,0,1,1,0,1,1,1,1,0,1,0,1,0,1,1,0,0,1,0,0,1,1,1,1,0,1,01},
    {1,0,0,0,0,1,1,1,0,0,0,1,0,0,0,0,0,0,0,1,0,0,0,1,0,0,0,0,0,1,0,1,0,1},
    {1,0,0,0,0,1,0,1,0,1,0,1,0,1,0,1,0,1,1,1,0,1,0,1,0,0,0,0,0,1,0,0,0,1},
    {1,0,0,0,0,1,0,1,0,1,0,1,0,1,0,1,0,0,0,0,0,1,0,1,0,0,0,0,0,1,0,0,0,1},
    {1,0,0,0,0,0,0,0,0,1,0,0,0,1,0,0,0,0,0,0,0,1,0,1,0,0,0,0,0,1,0,1,0,1},
    {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
    };
    
    private final int[][] mazeLevel3 = {
    {1,2,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
    {1,0,0,0,1,0,0,0,0,1,0,0,0,0,0,1,0,0,0,0,1,0,0,0,0,1,0,0,0,0,0,0,0,1},
    {1,0,1,0,1,0,1,1,0,1,0,1,1,1,0,1,0,1,1,0,1,0,1,1,0,1,0,1,0,1,1,1,0,1},
    {1,0,1,0,0,0,0,1,0,0,0,1,0,0,0,1,0,0,1,0,1,0,0,1,0,1,0,0,0,0,0,1,0,1},
    {1,1,1,1,1,1,0,1,1,1,0,1,1,0,1,1,1,0,1,1,1,0,1,1,1,1,1,0,1,1,0,0,0,1},
    {1,0,0,0,0,1,0,0,0,1,0,0,0,0,0,1,0,0,0,1,0,0,0,0,0,0,0,0,0,1,0,0,0,1},
    {1,0,1,1,0,1,1,1,0,1,1,1,1,1,0,1,1,1,0,1,1,1,1,1,0,1,0,1,0,1,1,1,0,1},
    {1,0,0,1,0,0,0,1,0,0,0,0,0,1,0,0,0,1,0,0,0,0,0,1,0,1,0,0,0,0,0,1,0,1},
    {1,1,0,1,1,1,0,1,1,1,1,0,1,1,1,0,1,1,1,1,0,1,1,1,0,1,1,1,0,1,0,1,0,1},
    {1,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
    {1,0,1,1,0,1,1,1,0,1,0,1,1,0,1,1,1,0,1,1,0,1,0,1,1,1,0,1,0,1,1,1,0,1},
    {1,0,0,1,0,0,1,0,0,1,0,0,1,0,0,1,0,0,1,0,0,1,0,0,1,0,0,1,0,0,1,0,0,1},
    {1,1,0,1,1,0,1,1,0,1,1,0,1,1,0,1,1,0,1,1,0,1,1,0,1,1,0,1,1,0,1,1,0,1},
    {1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
    {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
    {1,0,0,0,0,1,0,0,0,0,1,0,0,0,1,0,0,0,0,1,0,0,0,0,1,0,0,0,0,1,0,0,0,1},
    {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
};
    private final JPanel gamePanel;
    private final Timer gameLoop;

    public Lakers() {
        setTitle("Maze Game");
        setSize(mazeLevel1[0].length * TILE + 16, mazeLevel1.length * TILE + 39);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
       
        playerImage = new ImageIcon(getClass().getResource("lala (1).png")).getImage();
        loadLevel();

        gamePanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                drawMaze(g);
                drawFinish(g);
                drawEnemy(g);
                drawPlayer(g);
                
                

                if (gameOver) {
                    g.setColor(Color.RED);
                    g.setFont(new Font("Arial", Font.BOLD, 100));
                    g.drawString("GAME OVER", 370, 300);
                }
                if (won) {
                    g.setColor(Color.GREEN);
                    g.setFont(new Font("Arial", Font.BOLD, 100));
                    g.drawString("YOU WIN!", 370, 300);
                }
            }
        };
        gamePanel.setFocusable(true);
        gamePanel.addKeyListener(this);
        gamePanel.setBackground(Color.WHITE);
        gamePanel.setPreferredSize(
        new Dimension(mazeLevel1[0].length * TILE,
                  mazeLevel1.length * TILE)
);

        add(gamePanel, BorderLayout.CENTER);
        JButton restartButton = new JButton("Restart");
        restartButton.setFocusable(false);
        restartButton.addActionListener(e -> restartGame());
       JPanel bottomPanel = new JPanel();
       bottomPanel.add(restartButton);
       add(bottomPanel, BorderLayout.SOUTH);
        
        gamePanel.setBackground(Color.WHITE);
        add(gamePanel);
        addKeyListener(this);
        setFocusable(true);

        gameLoop = new Timer(16, this);
        gameLoop.start();
        setVisible(true);
    }
     class Node {
    int r, c;
    Node parent;
    Node(int r, int c, Node p) {
        this.r = r;
        this.c = c;
        this.parent = p;
    }
}
     
     private void randomizePositions(int[][] maze) {
    List<Point> freeCells = new ArrayList<>();
    for (int r = 0; r < maze.length; r++) {
        for (int c = 0; c < maze[r].length; c++) {
            if (maze[r][c] == 0) {
                freeCells.add(new Point(c, r));
            }
        }
    }

    if (freeCells.size() < 3) return; 

    
    Point playerStart = freeCells.remove((int)(Math.random() * freeCells.size()));
    p = playerStart.x * TILE + 5; 
    p1 = playerStart.y * TILE + 5;

    
    Point enemyStart = freeCells.remove((int)(Math.random() * freeCells.size()));
    ex = enemyStart.x * TILE;
    ey = enemyStart.y * TILE;

    
    Point finishPoint = freeCells.remove((int)(Math.random() * freeCells.size()));
    if (level == 1)
        finishLevel1.setLocation(finishPoint.x * TILE, finishPoint.y * TILE);
    else
        finishLevel2.setLocation(finishPoint.x * TILE, finishPoint.y * TILE);
}
     
     private Point findNextStep() {
    int[][] maze = (level == 1) ? mazeLevel1 : mazeLevel2;

    int sr = ey / TILE;
    int sc = ex / TILE;
    int tr = p1 / TILE;
    int tc = p / TILE;

    boolean[][] visited = new boolean[maze.length][maze[0].length];
    java.util.Queue<Node> q = new java.util.LinkedList<>();
    q.add(new Node(sr, sc, null));
    visited[sr][sc] = true;

    Node end = null;
    int[] dr = { -1, 1, 0, 0 };
    int[] dc = { 0, 0, -1, 1 };

    while (!q.isEmpty()) {
        Node cur = q.poll();
        if (cur.r == tr && cur.c == tc) {
            end = cur;
            break;
        }

        for (int i = 0; i < 4; i++) {
            int nr = cur.r + dr[i];
            int nc = cur.c + dc[i];

            if (nr >= 0 && nc >= 0 &&
                nr < maze.length && nc < maze[0].length &&
                maze[nr][nc] == 0 && !visited[nr][nc]) {

                visited[nr][nc] = true;
                q.add(new Node(nr, nc, cur));
            }
        }
    }

    if (end == null) return null;

    // backtrack to first step
    while (end.parent != null && end.parent.parent != null)
        end = end.parent;

    return new Point(end.c * TILE, end.r * TILE);
}
     
    private void loadLevel() {
        mazeWalls.clear();
        int[][] maze = (level == 1) ? mazeLevel1 : mazeLevel2;

        for (int r = 0; r < maze.length; r++)
        for (int c = 0; c < maze[r].length; c++)
            if (maze[r][c] == 1)
                mazeWalls.add(new Rectangle(c * TILE, r * TILE, TILE, TILE));
        randomizePositions(maze);
            
        }
    private void generateMaze() {
    // Initialize maze full of walls
    maze = new int[rows][cols];
    for (int r = 0; r < rows; r++)
        for (int c = 0; c < cols; c++)
            maze[r][c] = 1;

    // Start recursive backtracking from (1,1)
    carvePath(1, 1);

    // Optional: add some random extra walls for challenge
    addRandomWalls();
}

// Recursive backtracking
private void carvePath(int r, int c) {
    maze[r][c] = 0;
    int[][] dirs = {{0,1},{1,0},{0,-1},{-1,0}}; // Right, Down, Left, Up
    java.util.Collections.shuffle(java.util.Arrays.asList(dirs));
    for (int[] dir : dirs) {
        int nr = r + dir[0]*2;
        int nc = c + dir[1]*2;
        if (nr > 0 && nr < rows-1 && nc > 0 && nc < cols-1 && maze[nr][nc]==1) {
            maze[r + dir[0]][c + dir[1]] = 0; // carve between
            carvePath(nr, nc);
        }
    }
}

// Add some extra walls randomly to increase difficulty
private void addRandomWalls() {
    for (int i = 0; i < rows*cols/10; i++) {
        int r = 1 + (int)(Math.random()*(rows-2));
        int c = 1 + (int)(Math.random()*(cols-2));
        if (maze[r][c] == 0) maze[r][c] = 1;
    }
}
    
    private void drawPlayer(Graphics g) {
        g.drawImage(playerImage, p, p1, playerSize, playerSize, null);
    }

    private void drawEnemy(Graphics g) {
        g.setColor(Color.RED);
        g.fillOval(ex, ey, enemySize, enemySize);
    }

    private void drawMaze(Graphics g) {
        g.setColor(Color.BLACK);
        mazeWalls.forEach((wall) -> {
            g.fillRect(wall.x, wall.y, wall.width, wall.height);
        });
    }

    private void drawFinish(Graphics g) {
        g.setColor(Color.RED);
        Rectangle f = (level == 1) ? finishLevel1 : finishLevel2;
        g.fillRect(f.x, f.y, f.width, f.height);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (!won && !gameOver) {
            movePlayer();
            moveEnemy();
            checkWinOrLose();
        }
        gamePanel.repaint();
    }

    private void movePlayer() {
        int nx = p, ny = p1;
        if (l) nx -= playerSpeed;
        if (r) nx += playerSpeed;
        if (u) ny -= playerSpeed;
        if (d) ny += playerSpeed;

        if (!collides(new Rectangle(nx, p1, playerSize, playerSize))) p = nx;
        if (!collides(new Rectangle(p, ny, playerSize, playerSize))) p1 = ny;
    }

    private void moveEnemy() {
        Point next;
        next = findNextStep();
    if (next == null) return;

    if (ex < next.x) ex += enemySpeed;
    if (ex > next.x) ex -= enemySpeed;
    if (ey < next.y) ey += enemySpeed;
    if (ey > next.y) ey -= enemySpeed;

    }

    private boolean collides(Rectangle r) {
        return mazeWalls.stream().anyMatch((wall) -> (r.intersects(wall)));
    }

    private void checkWinOrLose() {
        Rectangle player = new Rectangle(p, p1, playerSize, playerSize);
        Rectangle enemy = new Rectangle(ex, ey, enemySize, enemySize);
        Rectangle finish = (level == 1) ? finishLevel1 : finishLevel2;

        if (player.intersects(enemy)) {
            gameOver = true;
            gameLoop.stop();
        }

        if (player.intersects(finish)) {
            if (level == 1) {
                level = 2;
                loadLevel();
            } else {
                won = true;
                gameLoop.stop();
            }
        }
    }
    private void restartGame() {
    level = 1;
    won = false;
    gameOver = false;

    
    l = r = u = d = false;

    loadLevel();

    if (!gameLoop.isRunning()) {
        gameLoop.start();
    }

    
    gamePanel.requestFocusInWindow();
    l = r = u = d = false;
    gamePanel.requestFocusInWindow();
}

    @Override
    public void keyPressed(KeyEvent e) { setKey(e.getKeyCode(), true); }
    @Override
    public void keyReleased(KeyEvent e) { setKey(e.getKeyCode(), false); }
    @Override
    public void keyTyped(KeyEvent e) {}

    private void setKey(int key, boolean pressed) {
        if (key == KeyEvent.VK_A || key == KeyEvent.VK_LEFT) l = pressed;
        if (key == KeyEvent.VK_D || key == KeyEvent.VK_RIGHT) r = pressed;
        if (key == KeyEvent.VK_W || key == KeyEvent.VK_UP) u = pressed;
        if (key == KeyEvent.VK_S || key == KeyEvent.VK_DOWN) d = pressed;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Lakers::new);
    }
}
    

    
