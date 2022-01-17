package guldilin.util;

public class ExceptionsUtils {
    public static Exception serializeRemoteException(Exception e) {
        String className = e.getClass().getName();
        switch (className) {
            case "guldilin.exceptions.EntryNotFound":
            case "guldilin.exceptions.ArgumentFormatException":
            case "guldilin.exceptions.StorageServiceRequestException":
                return new Exception(e.toString());
            default:
                return e;
        }
    }
}
