import java.util.ArrayList;

public class Main {

  /*
    Problem 1: 
    
    Create a program that populates your object structure for folders with this given array data which represents the below structure.
          A
       /  |  \
      B   C   D
     / \     / \
    E   F   G   K
       / \
      I   J
    
    Index 0 is the folder id, index 1 is the parent id, index 2 is the type (file or folder), and index 3 is the name
    
    i0 - folder id
    i1 - parent id
    i2 -
    String[][] folderData = {
                                {"A", null, "folder", “root”},
                                {"B", "A", "folder", “folder1”},
                                {"C", "A", "file", “file1”},
                                {"D", "A", "folder", “folder2”},
                                {"E", "B", "file", “file2”},
                                {"F", "B", "folder", “folder3”},
                                {"I", "F", "folder", “folder4”},
                                {"J", "F", "file", “file3”},
                                {"G", "D", "file", “file4”},
                                {"K", "D", "file", “file5”}
    };
    
    Assumptions
    • Data is given with Root folder always being first
    • Parent folders will exist before any children in the list
    
    My Assumptions
    • Data will be sorted based on depth as in given example above
    • ID are unique identifiers and no file/folder will have same id as another file/folder
    • Assume root folder is never empty
    • Folder can have more than 2 children as in given example above
    
    loadData(folderData);
    
    
    Problem 2:
    
    Count the number of nodes at each depth, essentially count the number of nodes in the entire Tree
    structure, starting from the root Folder node down.
    
  */
  

  /**
   * Returns the root node of Folder type of the populated Folder Tree Structure in the form of
   * File and Folder objects.
   * 
   * @param folderData given loaded 2d String folder Data with each row containing either File or Folder info
   *                   first row always the root
   * @return returns the root node of Folder type of the populated Tree Stucture
   */
  public static Folder populateFolderTreeStructure(String[][] folderData) { //Each row is a single file or folder
    //String folderData row example
    //Index 0 - folder id, index 1 - parent id, index 2 - type (file or folder), index 3 - name
    
    //Folder object constructor 
    //int id, String name, String parentId, Folder parent
    
    //File object constructor
    //int id, String name, String parentId, Folder parent
      
    String rootID = folderData[0][0];
    String rootParentID = folderData[0][1]; //always null
    String rootName = folderData[0][3];
    
    Folder root = new Folder(rootID, rootName, rootParentID, null);
    
    for (int i = 1; i < folderData.length; i++) {
      // Traverse each record in folderData after root and store record information
      String id = folderData[i][0];
      String parentID = folderData[i][1]; 
      String type = folderData[i][2];
      String name = folderData[i][3];
    
      // create Folder or file based on that record information extracted
      if (type.equals("folder")) {
        Folder folder = new Folder(id, name, parentID, null);
        insertFolder(root, parentID, folder);
      } else {
        File file = new File(id, name, parentID, null);
        insertFile(root, parentID, file);
      } 
    }
    return root;
  }

  /**
   * Recursively traverses a tree starting from root down and to insert the given Folder and assign 
   * a parent folder to this given folder by matching the given parentID.
   * 
   * @param root given root of Tree
   * @param parentID given parentID to match with an existing folderID
   * @param folder given folder to insert into Tree 
   * @return returns true if inserted successfully; otherwise false
   */
  public static Boolean insertFolder(Folder root, String parentID, Folder folder) {
    ArrayList<Folder> currentFolderNodeChildren = root.getFolderNodes();
    
    //check if root is parent
    if (root.getParent() == null && root.getId().equals(parentID)) {
      folder.setParent(root);
      root.addFolderNode(folder);
      return true;
    }
    
    //Check if root has no children
    if(root.getParent() == null && currentFolderNodeChildren.size() == 0) {
      return null;
    }
    
    //Check current node children
    for (Folder f : currentFolderNodeChildren) {
      if (f.getId().equals(parentID)) {
        folder.setParent(f);
        f.addFolderNode(folder);
        return true;
      }
    }
    
    //None of current node's children are parent matches, so now recursively call this method for each of those children
    for (Folder f : currentFolderNodeChildren) { // folder does not contain children then it stops recursively calling hence this is a base case
      insertFolder(f, parentID, folder);
    }
    
    return false;
  }


  /**
   * Recursively traverses a tree starting from root down and to insert the given File and assign 
   * a parent folder to this given file by matching the given parentID.
   * 
   * @param root given root of Tree
   * @param parentID given parentID to match with an existing folderID
   * @param file given file to insert into Tree 
   * @return returns true if inserted successfully; otherwise false
   */
  public static Boolean insertFile(Folder root, String parentID, File file) {
    //down, check children
    ArrayList<Folder> currentFolderNodeChildren = root.getFolderNodes();
    
    //check if root is parent
    if (root.getParent() == null && root.getId().equals(parentID)) {
      file.setParent(root);
      root.addFileNode(file);
      return true;
    }
    
    //Check if root has no children
    if(root.getParent() == null && currentFolderNodeChildren.size() == 0) {
      return null;
    }
    
    //Check current node children
    for (Folder f : currentFolderNodeChildren) {
      if (f.getId().equals(parentID)) {
        file.setParent(f);
        f.addFileNode(file);
        return true;
      }
    }
    
    //None of current node's children are parent matches, so now recursively call this method for each of those children
    for (Folder f : currentFolderNodeChildren) { // folder does not contain children then it stops recursively calling hence this is a base case
      insertFile(f, parentID, file);
    }
    
    return false;
  }

  /**
   * Returns a String representation of Tree using recursion.
   * 
   * @param root given root of tree to traverse and print it's content
   * @return returns a String representation of Tree using recursion
   */
  public static String printTree(Folder root) {
    String tree = "";
    ArrayList<Folder> currentFolderNodeChildren = root.getFolderNodes();
    for (Folder f : currentFolderNodeChildren) {
      tree += f.toString() + "\n\n" + printTree(f);
    }
    return tree;
  }

  /**
   * Returns the number of the total number of Tree nodes using recursion
   * 
   * @param root given root of Tree to traverse and count it's nodes
   * @return returns the total number of Tree nodes using recursion
   */
  public static int countTreeNodes(Folder root) {
    int count = 0;
    ArrayList<Folder> currentFolderNodeChildren = root.getFolderNodes();
    if (root.getParent() == null) {
      count += 1 + root.getFileNodes().size() + root.getFolderNodes().size();
    }
    for (Folder f : currentFolderNodeChildren) {
      count += f.getFileNodes().size() + f.getFolderNodes().size() + countTreeNodes(f);
    }
    return count;
  }

  /**
   * Populates Folder Tree Structure and prints to console the Tree Representation.
   * Counts total number of nodes in Tree and prints to console.
   * 
   * @param args arguments
   */
  public static void main(String[] args) {
    String[][] folderData = {
                              {"A", null, "folder", "root"},
                              {"B", "A", "folder", "folder1"},
                              {"C", "A", "file", "file1"},
                              {"D", "A", "folder", "folder2"},
                              {"E", "B", "file", "file2"},
                              {"F", "B", "folder", "folder3"},
                              {"I", "F", "folder", "folder4"},
                              {"J", "F", "file", "file3"},
                              {"G", "D", "file", "file4"},
                              {"K", "D", "file", "file5"}
                            };
  
    Folder root = populateFolderTreeStructure(folderData);
    
    String tree = root.toString() + '\n';
    tree += printTree(root);
    
    System.out.println("Tree Representation:\n\n" + tree);
    System.out.println("Total Count of Nodes:" + countTreeNodes(root));
  }
}