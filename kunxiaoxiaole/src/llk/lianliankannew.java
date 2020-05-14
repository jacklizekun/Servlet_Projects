package llk;

import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;

@SuppressWarnings("unused")
public class lianliankannew implements ActionListener {

	JFrame mainFrame; // 主面板
	Container thisContainer;
	JPanel centerPanel, southPanel, northPanel; // 子面板
	JButton diamondsButton[][] = new JButton[10][10];// 游戏按钮数组
	ImageIcon[][] diamondsImg = new ImageIcon[10][10];
	JButton resetButton, newlyButton; // 退出，重列，重新开始按钮
	JLabel fractionLable = new JLabel("0"); // 分数标签
	JButton firstButton, secondButton; // 分别记录两次被选中的按钮
	int grid[][] = new int[12][12];// 储存游戏按钮位置
	static boolean pressInformation = false; // 判断是否有按钮被选中
	int x0 = 0, y0 = 0, x = 0, y = 0, fristMsg = 0, secondMsg = 0, validateLV; // 游戏按钮的位置坐标
	int i, j, k, n;// 消除方法控制
	/*
	 * public MyLink() { this.setDefaultCloseOperation(EXIT_ON_CLOSE); }
	 */

	public void init() {
		mainFrame = new JFrame("连连看");
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainFrame.setResizable(false);
		thisContainer = mainFrame.getContentPane();
		thisContainer.setLayout(new BorderLayout());
		centerPanel = new JPanel();
		centerPanel.setLayout(new GridLayout(10, 10));
		southPanel = new JPanel();
		southPanel.setLayout(new GridLayout(1, 2));
		northPanel = new JPanel();
		thisContainer.add(centerPanel, "Center");
		thisContainer.add(southPanel, "South");
		thisContainer.add(northPanel, "North");

		for (int cols = 0; cols < 10; cols++) {
			for (int rows = 0; rows < 10; rows++) {
				String s = "images/" + (grid[cols + 1][rows + 1]) + ".jpg";
				diamondsImg[cols][rows] = new ImageIcon(s);
				diamondsButton[cols][rows] = new JButton(diamondsImg[cols][rows]);
				diamondsButton[cols][rows].setSize(64,58);
				diamondsButton[cols][rows].addActionListener(this);
				centerPanel.add(diamondsButton[cols][rows]);
			}
		}

		resetButton = new JButton("reset");
		resetButton.setSize(312, 96);
		resetButton.addActionListener(this);
		newlyButton = new JButton("new");
		newlyButton.addActionListener(this);
		newlyButton.setSize(312, 96);
		southPanel.add(newlyButton);
		southPanel.add(resetButton);

		fractionLable.setText(String.valueOf(Integer.parseInt(fractionLable.getText())));
		northPanel.add(fractionLable);
		mainFrame.setBounds(400, 50, 700, 1000);

		mainFrame.setVisible(true);
	}

	public void randomBuild() {
		int randoms, cols = 0, rows=0;
		for (int twins = 1; twins <= 50; twins++) {//两两一组，共有50组
			randoms = (int) (Math.random() * 3 + 1);//等于123,随机挑选3张图片
			for (int alike = 1; alike <= 2; alike++) {
				cols = (int) (Math.random() * 10 + 1);//随机数1到10
				rows = (int) (Math.random() * 10 + 1);
				while (grid[cols][rows] != 0) {
					cols = (int) (Math.random() * 10 + 1);
					rows = (int) (Math.random() * 10 + 1);
				}
				this.grid[cols][rows] = randoms;

			}this.grid[cols][rows] = 3+randoms;
		}
	}

	public void fraction() {
		fractionLable.setText(String.valueOf(Integer.parseInt(fractionLable.getText()) + 100));
	}

	public void reload() {
		int save[] = new int[100];
		int n = 0, cols, rows;
		int grid[][] = new int[12][12];
		for (int i = 0; i <= 10; i++) {
			for (int j = 0; j <= 10; j++) {
				if (this.grid[i][j] != 0) {
					save[n] = this.grid[i][j];
					n++;
				}
			}
		}
		n = n - 1;
		this.grid = grid;
		while (n >= 0) {
			cols = (int) (Math.random() * 10 + 1);
			rows = (int) (Math.random() * 10 + 1);
			while (grid[cols][rows] != 0) {
				cols = (int) (Math.random() * 10 + 1);
				rows = (int) (Math.random() * 10 + 1);
			}
			this.grid[cols][rows] = save[n];
			n--;
		}
		mainFrame.setVisible(false);
		pressInformation = false; // 这里一定要将按钮点击信息归为初始
		init();
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				if (grid[i + 1][j + 1] == 0)
					diamondsButton[i][j].setVisible(false);
			}
		}
	}

	public void estimateEven(int placeX, int placeY, JButton bz) {
		if (pressInformation == false) {
			x = placeX;
			y = placeY;
			secondMsg = grid[x][y];
			secondButton = bz;
			pressInformation = true;
		} else {
			x0 = x;
			y0 = y;
			fristMsg = secondMsg;
			firstButton = secondButton;
			x = placeX;
			y = placeY;
			secondMsg = grid[x][y];
			secondButton = bz;
			if (fristMsg == secondMsg-3 ||fristMsg == secondMsg+3 && secondButton != firstButton) {
				xiao();
			}
		}
	}

	public void xiao() { // 相同的情况下能不能消去。仔细分析，不一条条注释
		if ((x0 == x && (y0 == y + 1 || y0 == y - 1)) || ((x0 == x + 1 || x0 == x - 1) && (y0 == y))) { // 判断是否相邻
			remove();
		} else {
			for (j = 0; j < 12; j++) {
				if (grid[x0][j] == 0) { // 判断第一个按钮同行哪个按钮为空
					if (y > j) { // 如果第二个按钮的Y坐标大于空按钮的Y坐标说明第一按钮在第二按钮左边
						for (i = y - 1; i >= j; i--) { // 判断第二按钮左侧直到第一按钮中间有没有按钮
							if (grid[x][i] != 0) {
								k = 0;
								break;
							} else {
								k = 1;
							} // K=1说明通过了第一次验证
						}
						if (k == 1) {
							linePassOne();
						}
					}
					if (y < j) { // 如果第二个按钮的Y坐标小于空按钮的Y坐标说明第一按钮在第二按钮右边
						for (i = y + 1; i <= j; i++) { // 判断第二按钮左侧直到第一按钮中间有没有按钮
							if (grid[x][i] != 0) {
								k = 0;
								break;
							} else {
								k = 1;
							}
						}
						if (k == 1) {
							linePassOne();
						}
					}
					if (y == j) {
						linePassOne();
					}
				}

				if (k == 2) {
					if (x0 == x) {
						remove();
					}
					if (x0 < x) {
						for (n = x0; n <= x - 1; n++) {
							if (grid[n][j] != 0) {
								k = 0;
								break;
							}
							if (grid[n][j] == 0 && n == x - 1) {
								remove();
							}
						}
					}
					if (x0 > x) {
						for (n = x0; n >= x + 1; n--) {
							if (grid[n][j] != 0) {
								k = 0;
								break;
							}
							if (grid[n][j] == 0 && n == x + 1) {
								remove();
							}
						}
					}
				}
			}

			for (i = 0; i < 12; i++) { // 列
				if (grid[i][y0] == 0) {
					if (x > i) {
						for (j = x - 1; j >= i; j--) {
							if (grid[j][y] != 0) {
								k = 0;
								break;
							} else {
								k = 1;
							}
						}
						if (k == 1) {
							rowPassOne();
						}
					}
					if (x < i) {
						for (j = x + 1; j <= i; j++) {
							if (grid[j][y] != 0) {
								k = 0;
								break;
							} else {
								k = 1;
							}
						}
						if (k == 1) {
							rowPassOne();
						}
					}
					if (x == i) {
						rowPassOne();
					}
				}
				if (k == 2) {
					if (y0 == y) {
						remove();
					}
					if (y0 < y) {
						for (n = y0; n <= y - 1; n++) {
							if (grid[i][n] != 0) {
								k = 0;
								break;
							}
							if (grid[i][n] == 0 && n == y - 1) {
								remove();
							}
						}
					}
					if (y0 > y) {
						for (n = y0; n >= y + 1; n--) {
							if (grid[i][n] != 0) {
								k = 0;
								break;
							}
							if (grid[i][n] == 0 && n == y + 1) {
								remove();
							}
						}
					}
				}
			}
		}
	}

	public void linePassOne() {
		if (y0 > j) { // 第一按钮同行空按钮在左边
			for (i = y0 - 1; i >= j; i--) { // 判断第一按钮同左侧空按钮之间有没按钮
				if (grid[x0][i] != 0) {
					k = 0;
					break;
				} else {
					k = 2;
				} // K=2说明通过了第二次验证
			}
		}
		if (y0 < j) { // 第一按钮同行空按钮在与第二按钮之间
			for (i = y0 + 1; i <= j; i++) {
				if (grid[x0][i] != 0) {
					k = 0;
					break;
				} else {
					k = 2;
				}
			}
		}
	}

	public void rowPassOne() {
		if (x0 > i) {
			for (j = x0 - 1; j >= i; j--) {
				if (grid[j][y0] != 0) {
					k = 0;
					break;
				} else {
					k = 2;
				}
			}
		}
		if (x0 < i) {
			for (j = x0 + 1; j <= i; j++) {
				if (grid[j][y0] != 0) {
					k = 0;
					break;
				} else {
					k = 2;
				}
			}
		}
	}

	public void remove() {
		firstButton.setVisible(false);
		secondButton.setVisible(false);
		fraction();
		pressInformation = false;
		k = 0;
		grid[x0][y0] = 0;
		grid[x][y] = 0;
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == newlyButton) {
			int grid[][] = new int[12][12];
			this.grid = grid;
			randomBuild();
			mainFrame.setVisible(false);
			pressInformation = false;
			init();
		}

		if (e.getSource() == resetButton)
			reload();
		for (int cols = 0; cols < 10; cols++) {
			for (int rows = 0; rows < 10; rows++) {
				if (e.getSource() == diamondsButton[cols][rows])
					estimateEven(cols + 1, rows + 1, diamondsButton[cols][rows]);
			}
		}
	}

	public static void main(String[] args) {
		lianliankannew llk = new lianliankannew();
		llk.randomBuild();
		llk.init();
	}
}