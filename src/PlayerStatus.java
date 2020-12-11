
public class PlayerStatus {

	private final String nickname;
	private int score;
	private int lives;
	private int health = 100;
	private String weaponInHand;
	private double positionX;
	private double positionY;
	private static String gameName;

	public PlayerStatus(String nickname) {
		this.nickname = nickname;
	}

	public PlayerStatus(String nickname, int lives) {
		this(nickname);
		this.lives = lives;
	}

	public PlayerStatus(String nickname, int lives, int score) {
		this(nickname, lives);
		this.score = score;
	}

	public void findArtifact(int artifactCode) {

		if (isPerfect(artifactCode)) {
			score += 5000;
			lives++;
			health = 100;

		} else if (isPrime(artifactCode)) {
			score += 1000;
			lives += 2;
			health += 25;
			if (health > 100) {
				health = 100;
			}

		} else if (isEvenAndDigitsSumDiv3(artifactCode)) {
			score -= 3000;
			if (score <= 0) {
				score = 0;
			}

			health -= 25;
			if (health <= 0) {
				health = 100;
				lives--;
				if (lives == 0) {
					System.out.println("==================  Game Over  ===================");
					return;
				}
			}

		} else {
			score += artifactCode;
		}
	}

	private static boolean isPerfect(int number) {

		int sum = 0;
		for (int i = 1; i <= number / 2; i++) {
			if (number % i == 0) {
				sum += i;
			}
		}
		if (sum == number) {
			return true;
		} else {
			return false;
		}
	}

	private static boolean isPrime(int number) {

		if (number < 2) {
			return false;
		}

		for (int i = 2; i <= number / 2; i++) {
			if (number % i == 0) {
				return false;
			}
		}
		return true;
	}

	private static boolean isEvenAndDigitsSumDiv3(int number) {
		int sum = 0;
		while (number != 0) {
			sum += number % 10;
			number /= 10;
		}
		if (number % 2 == 0 && sum % 3 == 0) {
			return true;
		} else {
			return false;
		}
	}

	public boolean setWeaponInHand(String weaponInHand) {

		if (weaponInHand.equals("knife")) {
			if (score >= 1000) {
				this.weaponInHand = weaponInHand;
				score -= 1000;
				System.out.println("Arma a fost actualizata");
				return true;
			} else {
				System.out.println("Nu ai suficiente puncte");
				return false;
			}

		} else if (weaponInHand.equals("sniper")) {
			if (score >= 10000) {
				this.weaponInHand = weaponInHand;
				score -= 10000;
				System.out.println("Arma a fost actualizata");
				return true;
			} else {
				System.out.println("Nu ai suficiente puncte");
				return false;
			}

		} else if (weaponInHand.equals("kalashnikov")) {
			if (score >= 20000) {
				this.weaponInHand = weaponInHand;
				score -= 20000;
				System.out.println("Arma a fost actualizata");
				return true;
			} else {
				System.out.println("Nu ai suficiente puncte");
				return false;
			}

		} else {
			System.out.println("Arma invalida");
			return false;
		}

	}

	public String getWeaponInHand() {
		return weaponInHand;
	}

	public double getPositionX() {
		return positionX;
	}

	public void setPositionX(double positionX) {
		this.positionX = positionX;
	}

	public double getPositionY() {
		return positionY;
	}

	public void setPositionY(double positionY) {
		this.positionY = positionY;
		
	}

	static String getGameName() {
		return gameName; 
	}

	static void setGameName(String gameName) {
		PlayerStatus.gameName = gameName;
	}

	public void movePlayerTo(double positionX, double positionY) {
		this.positionX = positionX;
		this.positionY = positionY;
		System.out.println("Pozitia jucatorului a fost actualizata");
	}

	public String getNickname() {
		return nickname;
	}

	public boolean shouldAttackOponent(PlayerStatus oponent) {

		if (this.weaponInHand.equals(oponent.weaponInHand)) {
			double thisProbability = (3 * this.health + this.score / 1000) / 4;
			double oponentProbability = (3 * oponent.health + oponent.score / 1000) / 4;
			System.out.println(thisProbability);
			System.out.println(oponentProbability);
			if (thisProbability > oponentProbability) {
				return true;
			} else if (thisProbability <= oponentProbability) {
				return false;
			}

		} else {

			if (this.getDistance(oponent) > 1000) {
				if (this.weaponInHand.equals("sniper")
						|| (this.weaponInHand.equals("kalashnikov") && oponent.weaponInHand.equals("knife"))) {
					return true;
				}

			} else if (this.getDistance(oponent) <= 1000) {
				if (this.weaponInHand.equals("kalashnikov")
						|| (this.weaponInHand.equals("sniper") && oponent.weaponInHand.equals("knife"))) {
					return true;
				}
			}
		}
		return false;
	}

	private double getDistance(PlayerStatus oponent) {
		double dist = -1;
		if (oponent != null) {
			dist = Math.sqrt((this.positionX - oponent.positionX) * (this.positionX - oponent.positionX)
					+ (this.positionY - oponent.positionY) * (this.positionY - oponent.positionY));
		}
		return dist;
	}

	public String toString() {
		System.out.println("PLAYER STATUS");
		return "->Player name: " + this.getNickname() + " \n->Lives: " + this.lives + " \n->Health: " + this.health
				+ " \n->Score: " + this.score + " \n->Weapon: " + this.getWeaponInHand() + " \n->Position:("
				+ this.getPositionX() + "," + this.getPositionY() + ")";
	}

}
