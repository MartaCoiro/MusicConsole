package Class;

import java.io.Serializable;

public class image implements Serializable {

	private static final long serialVersionUID = 1L;
	//valori della tabella
		Integer imageId;
		String imageFileName;

		public image() { 
			imageId = 0;
			imageFileName = "";
			}
		
		public Integer getImageId() {
			return imageId;
		}


		public void setImageId(Integer imageId) {
			this.imageId = imageId;
		}


		public String getImageFileName() {
			return imageFileName;
		}


		public void setImageFileName(String imageFileName) {
			this.imageFileName = imageFileName;
		}


		public void print() {
			System.out.printf("%10d | %8s \n", imageId,imageFileName);
		}
		
		/*public boolean valid(String utente) {
			if(this.getNickname()==utente) {
				return true;
			}
				else return false;
		}*/
		
		@Override
		public String toString() {
			return imageId + "," +  imageFileName;
		}
		
		@Override  //ci dice se ci sono errori
		public boolean equals(Object other) { //restituisce true se vero
			return this.getImageFileName() == ((image) other).getImageFileName();
		}
		
		
		
}
