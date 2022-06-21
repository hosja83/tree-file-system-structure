public class File {
    
  private String id;
  private String name;
  private String parentId;
  private Folder parent;

  File(String id, String name, String parentId, Folder parent) {
    this.id = id;
    this.name = name;
    this.parent = parent;
    this.parentId = parentId;
  }

  public String getId() {
    return this.id;
  }

  public String getName() {
    return this.name;
  }

  public String getParentID() {
    return this.parentId;
  }

  public Folder getParent() {
    return this.parent;
  }
  
  public void setParent(Folder p) {
    this.parent = p;
  }
}