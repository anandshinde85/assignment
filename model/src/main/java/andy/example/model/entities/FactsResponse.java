package andy.example.model.entities;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * Entity class encapsulating Facts API response
 *
 * @author Anand Shinde
 */
public class FactsResponse implements Parcelable {
    private String title;
    private List<Row> rows = null;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Row> getRows() {
        return rows;
    }

    public void setRows(List<Row> rows) {
        this.rows = rows;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.title);
        dest.writeTypedList(this.rows);
    }

    public FactsResponse() {
    }

    protected FactsResponse(Parcel in) {
        this.title = in.readString();
        this.rows = in.createTypedArrayList(Row.CREATOR);
    }

    public static final Parcelable.Creator<FactsResponse> CREATOR =
            new Parcelable.Creator<FactsResponse>() {
                @Override
                public FactsResponse createFromParcel(Parcel source) {
                    return new FactsResponse(source);
                }

                @Override
                public FactsResponse[] newArray(int size) {
                    return new FactsResponse[size];
                }
            };
}