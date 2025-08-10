import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.ClipboardOwner;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.Toolkit;
import java.io.*;
public class copyTask extends Thread implements ClipboardOwner{

   interface EntryListener {
    void onCopy(String data);
  }
  
  private Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
  private EntryListener entryListener;






   public void setEntryListener(EntryListener entryListener) {
    this.entryListener = entryListener;
  }

  
  @Override
  public void lostOwnership(Clipboard c, Transferable t) {
    try {
      sleep(200);
    } catch (Exception e) {
    }

    Transferable contents = c.getContents(this);
    processContents(contents);
    regainOwnership(c, contents);
  }




    public void processContents(Transferable t) {
    try {
      String what = (String) (t.getTransferData(DataFlavor.stringFlavor));

      // we alert our entry listener
      if (entryListener != null) {
        entryListener.onCopy(what);
      }
    } catch (Exception e) {
    }
  }

  public void regainOwnership(Clipboard c, Transferable t) {
    c.setContents(t, this);
  }

  public void run() {
    Transferable transferable = clipboard.getContents(this);
    regainOwnership(clipboard, transferable);

    while(true);
  }





  

    public static void main(String[] args) {
    copyTask listener = new copyTask();

    listener.setEntryListener(data -> {
        System.out.println("Copied text: " + data);
    });

    listener.start();
    System.out.println("Clipboard listener started. Press Ctrl+C to stop.");
}
/* 





















     public String getClipboardContents() {
    String result = "";
    Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
    //odd: the Object param of getContents is not currently used
    Transferable contents = clipboard.getContents(null);
    boolean hasTransferableText =
      (contents != null) &&
      contents.isDataFlavorSupported(DataFlavor.stringFlavor)
    ;
    if (hasTransferableText) {
      try {
        result = (String)contents.getTransferData(DataFlavor.stringFlavor);
      }
      catch (UnsupportedFlavorException | IOException ex){
        System.out.println(ex);
        ex.printStackTrace();
      }
    }
    return result;
    
  }
*/
}

