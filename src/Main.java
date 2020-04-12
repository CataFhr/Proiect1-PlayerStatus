
public class Main {

	public static void main(String[] args) {

		PlayerStatus.setGameName("Counter Strike");
		System.out.println(PlayerStatus.getGameName());

		PlayerStatus player = new PlayerStatus("Cata", 2, 15000);
		player.movePlayerTo(3.0, 2.0);
		System.out.println(player);

//		player.findArtifact(28);
//		System.out.println(player);
//		player.findArtifact(13);
//		System.out.println(player);
		player.findArtifact(42);
		System.out.println(player);
//		player.findArtifact(32);
//		System.out.println(player);

//		player.setWeaponInHand("kalashnikov");
		player.setWeaponInHand("sniper");
		System.out.println(player);

		PlayerStatus oponent = new PlayerStatus("Oponent", 1, 20000);
		oponent.movePlayerTo(410.0, 920.0);
		oponent.setWeaponInHand("Kalashnikov");
		System.out.println(oponent);

//	    System.out.println(player.getDistance(oponent));
		System.out.println(player.shouldAttackOponent(oponent));

	}

}
