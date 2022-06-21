import java.util.ArrayList;

public class Folder {

  private String id;
  private String name;
  private String parentId;
  private Folder parent;
  private ArrayList<File> fileNodes;
  private ArrayList<Folder> folderNodes;

  Folder(String id, String name, String parentId, Folder parent) {
    this.id = id;
    this.name = name;
    this.parentId = parentId;
    this.parent = parent;
    this.fileNodes = new ArrayList<File>();
    this.folderNodes = new ArrayList<Folder>();
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

  public ArrayList<File> getFileNodes() {
    return this.fileNodes;
  }

  public void addFileNode(File fileNode) {
    this.fileNodes.add(fileNode);
  }

  public File getFileNode(int index) {
    return this.fileNodes.get(index);
  }

  public ArrayList<Folder> getFolderNodes() {
    return this.folderNodes;
  }

  public Folder getFolderNode(int index) {
    return this.folderNodes.get(index);
  }

  public void addFolderNode(Folder folderNode) {
    this.folderNodes.add(folderNode);
  }

  public String toString() {
      String folderRepresentation = this.id + ": " + this.name + " has " + this.fileNodes.size() + " file(s) and " + this.folderNodes.size() + " folder(s), " + 
      "it's parent is " + this.parentId + '\n';
      
      folderRepresentation += this.id + ": " + this.name + " contains file(s): ";
     
      if (this.fileNodes.size() == 0) {
          folderRepresentation += "None";
      }
      
      for (int i = 0; i < this.fileNodes.size(); i++) {
          if ( i == this.fileNodes.size() - 1) {
              folderRepresentation += this.fileNodes.get(i).getId() + " - " + this.fileNodes.get(i).getName();
              break;
          }
          folderRepresentation += this.fileNodes.get(i).getId() + " - " + this.fileNodes.get(i).getName() + ", ";
      }
      
      folderRepresentation += "\n" + this.id + ": " + this.name + " contains folder(s): ";
      
      if (this.folderNodes.size() == 0) {
          folderRepresentation += "None";
      }
      
      for (int i = 0; i < this.folderNodes.size(); i++) {
          if ( i == this.folderNodes.size() - 1) {
              folderRepresentation += this.folderNodes.get(i).getId() + " - " + this.folderNodes.get(i).getName();
              break;
          }
          folderRepresentation += this.folderNodes.get(i).getId() + " - " + this.folderNodes.get(i).getName() + ", ";
      }
      
      return folderRepresentation;
  }
}