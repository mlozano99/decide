package es.dabdm.decide.util;

public class LVI_generico {	 
	
	private String title;
	private int id;
	
    public LVI_generico(String title, int id) {
        super();
        this.title = title;
        this.id=id;
    }

    public LVI_generico(String title) {
        super();
        this.title = title;
    }    
    
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

    
    
}
