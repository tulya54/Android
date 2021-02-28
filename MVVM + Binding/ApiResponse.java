import io.reactivex.annotations.NonNull;
import io.reactivex.annotations.Nullable;
import itest.kz.util.Status;
import retrofit2.Response;

public class ApiResponse {
    public final Status status;

    @Nullable
    public final Response data;

    @Nullable
    public final Response error;

    @Nullable
    public final Throwable throwable;

    private ApiResponse(Status status, @Nullable Response data, @Nullable Response error, @Nullable Throwable throwable) {
        this.status = status;
        this.data = data;
        this.error = error;
        this.throwable = throwable;
    }

    public static ApiResponse loading() {
        return new ApiResponse(Status.LOADING, null, null, null);
    }

    public static ApiResponse success(@NonNull Response data) {
        return new ApiResponse(Status.SUCCESS, data, null, null);
    }

    public static ApiResponse error(@NonNull Response error) {
        return new ApiResponse(Status.ERROR, null, error, null);
    }

    public static ApiResponse throwable(@NonNull Throwable throwable) {
        return new ApiResponse(Status.THROWABLE, null, null, throwable);
    }

}
