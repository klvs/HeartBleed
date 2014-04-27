public class TLSmsg{
		/*
		 * TLS packet headers contain the type, 
		 * version of tls/ssl,
		 * and the length
		 */
		byte[] body;
		int type, version, length;
		
		public TLSmsg(int type, int version, int length, byte[] body){
			this.type = type;
			this.version = version;
			this.length = length;
			this.body = body;
		}
	}