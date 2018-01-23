package arun.kg;

class Toast {

    // To store the current status of this toast
    private ToastStatus status;

    private int id;

    Toast(ToastStatus status, int id) {
        this.status = status;
        this.id = id;
    }

    // Expose the encapsulated toast status
    public ToastStatus getStatus() {
        return this.status;
    }


    void butterToast() {
        this.status = ToastStatus.BUTTERED;
    }

    void peanutButterToast() {
        this.status = ToastStatus.PEANUTBUTTERED;
    }

    void jamToast() {
        this.status = ToastStatus.JAMMED;
    }

    void jellyToast() {
        this.status = ToastStatus.JELLIED;
    }

    void setId(int id) {
        this.id = id;
    }

    int getId() {
        return id;
    }
}
