package gestioneProdotti;

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

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((imageFileName == null) ? 0 : imageFileName.hashCode());
			result = prime * result + ((imageId == null) ? 0 : imageId.hashCode());
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (!(obj instanceof image))
				return false;
			image other = (image) obj;
			if (imageFileName == null) {
				if (other.imageFileName != null)
					return false;
			} else if (!imageFileName.equals(other.imageFileName))
				return false;
			if (imageId == null) {
				if (other.imageId != null)
					return false;
			} else if (!imageId.equals(other.imageId))
				return false;
			return true;
		}
		
		/*public boolean valid(String utente) {
			if(this.getNickname()==utente) {
				return true;
			}
				else return false;
		}*/
		
		
		
		
		
}
