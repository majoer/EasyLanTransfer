package easylantransfer.graphics.transferrer;

import Entity.FileDescription;
import easylantransfer.instance.FileList;
import easylantransfer.instance.TableSingleton;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DropTargetDragEvent;
import java.awt.dnd.DropTargetDropEvent;
import java.awt.dnd.DropTargetEvent;
import java.awt.dnd.DropTargetListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DragDrop implements DropTargetListener {

    public DragDrop() {
    }

    @Override
    public void dragEnter(DropTargetDragEvent dtde) {
    }

    @Override
    public void dragOver(DropTargetDragEvent dtde) {
    }

    @Override
    public void dropActionChanged(DropTargetDragEvent dtde) {
    }

    @Override
    public void dragExit(DropTargetEvent dte) {
    }

    @Override
    public void drop(DropTargetDropEvent dtde) {
        dtde.acceptDrop(DnDConstants.ACTION_COPY);
        Transferable t = dtde.getTransferable();
        DataFlavor[] flavors = t.getTransferDataFlavors();

        for (DataFlavor d : flavors) {
            if (d.isFlavorJavaFileListType()) {
                try {
                    List<File> files = (List) t.getTransferData(d);
                    FileList.getInstance(true).populate(toFileDescriptionArray(files), false);
                } catch (UnsupportedFlavorException | IOException ex) {
                    Logger.getLogger(DragDrop.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        dtde.dropComplete(true);
    }
    
    private List<FileDescription> toFileDescriptionArray(List<File> files) {
        List<FileDescription> res = new ArrayList<>();
        
        for(File f : files) {
            res.add(new FileDescription(f));
        }
        return res;
    }
}
