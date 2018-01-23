package arun.kg;

public class Toast {

    // To store the current status of this toast
    private ToastStatus status;

    private int id;

    public Toast(ToastStatus status, int id) {
        this.status = status;
        this.id = id;
    }

    // Expose the encapsulated toast status
    public ToastStatus getStatus() {
        return this.status;
    }


    public void butterToast() {
        this.status = ToastStatus.BUTTERED;
    }

    public void peanutButterToast() {
        this.status = ToastStatus.PEANUTBUTTERED;
    }

    public void jamToast() {
        this.status = ToastStatus.JAMMED;
    }

    public void jellyToast() {
        this.status = ToastStatus.JELLIED;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
