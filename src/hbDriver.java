import java.io.IOException;


public class hbDriver {

	public static void main(String[] args) {
		HeartBleed hb = new HeartBleed();
		
		try{
			hb.connect("***server***", 443);
			hb.hello();
			hb.heartBeat("Test");
		}
		catch(IOException e){
			e.getMessage();
		}
	}

}
