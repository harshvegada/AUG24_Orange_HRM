package constants;

public enum HttpStatusCode {
    OK(200), CREATED(201), NOT_FOUND(404), NO_CONTENT(204);

    private int statusCode;

    HttpStatusCode(int status){
        statusCode = status;
    }

    public int getStatusCode(){
        return statusCode;
    }

}