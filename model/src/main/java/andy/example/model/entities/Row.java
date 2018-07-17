package andy.example.model.entities;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Added class which encapsulates response from FactsAPI
 *
 * @author Anand Shinde
 */
public class Row implements Parcelable {
  private String title;
  private String description;
  private String imageHref;

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getImageHref() {
    return imageHref;
  }

  public void setImageHref(String imageHref) {
    this.imageHref = imageHref;
  }

  @Override
  public int describeContents() {
    return 0;
  }

  @Override
  public void writeToParcel(Parcel dest, int flags) {
    dest.writeString(this.title);
    dest.writeString(this.description);
    dest.writeString(this.imageHref);
  }

  public Row() {
  }

  protected Row(Parcel in) {
    this.title = in.readString();
    this.description = in.readString();
    this.imageHref = in.readString();
  }

  public static final Parcelable.Creator<Row> CREATOR = new Parcelable.Creator<Row>() {
    @Override
    public Row createFromParcel(Parcel source) {
      return new Row(source);
    }

    @Override
    public Row[] newArray(int size) {
      return new Row[size];
    }
  };
}