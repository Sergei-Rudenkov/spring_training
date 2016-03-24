package wo_spring_realization.loggers;

import org.apache.commons.io.FileUtils;
import wo_spring_realization.event.Event;

import java.io.File;
import java.io.IOException;

/**
 * Created by Sergei_Rudenkov on 10/29/2015.
 */
public class FileEventLogger implements IEventLogger {

    protected File file;
    private String fileName;

    public FileEventLogger(String fileName) {
        this.fileName = fileName;
    }

    public void logEvent(Event event){
        try {
            FileUtils.writeStringToFile(file, event.toString(), true);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void init() throws IOException {
        this.file = new File(fileName);
        if(!file.canWrite()) throw new IOException(String.format("File '%s' is not writable", fileName));
    }
}
