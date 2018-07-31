package andy.example.wiproassignment.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.squareup.picasso.Picasso;

import java.util.List;

import andy.example.commons.utils.CollectionUtil;
import andy.example.model.entities.Row;
import andy.example.wiproassignment.R;
import andy.example.wiproassignment.viewholders.FactsViewHolder;

/**
 * Adapter to display and facts list.
 *
 * @author Anand Shinde
 */
public class FactsAdapter extends RecyclerView.Adapter<FactsViewHolder> {
  private final Context context;
  private List<Row> rowList;

  public FactsAdapter(Context context, List<Row> rowList) {
    this.context = context;
    this.rowList = rowList;
  }

  @NonNull
  @Override
  public FactsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    View view =
        LayoutInflater.from(context).inflate(R.layout.card_facts, parent, false);
    return new FactsViewHolder(view);
  }

  @Override
  public void onBindViewHolder(@NonNull FactsViewHolder holder, int position) {
    Row row = rowList.get(position);
    if (null == row) {
      holder.getTvTitle().setText(context.getString(R.string.unavailable));
      holder.getTvDescription().setText(context.getString(R.string.unavailable));
      Picasso.with(context)
          .load(R.drawable.ic_place_holder)
          .into(holder.getIvFact());
    } else {
      String rowTitle = row.getTitle();
      String title =
          TextUtils.isEmpty(rowTitle) ? context.getString(R.string.unavailable) : rowTitle;
      holder.getTvTitle().setText(title);

      String rowDescription = row.getDescription();
      String description =
          TextUtils.isEmpty(rowDescription) ? context.getString(R.string.unavailable) :
              rowDescription;
      holder.getTvDescription().setText(description);

      String imageLink = row.getImageHref();
      Picasso.with(context)
          .load(imageLink)
          .placeholder(R.drawable.ic_place_holder)
          .error(R.drawable.ic_place_holder_error)
          .into(holder.getIvFact());
    }
  }

  /**
   * Returns the total number of items in the data set held by the adapter.
   *
   * @return The total number of items in this adapter.
   */
  @Override
  public int getItemCount() {
    return CollectionUtil.isNotEmpty(rowList) ? rowList.size() : 0;
  }

  /**
   * Method to update data set
   *
   * @param rowList - row list
   */
  public void updateDataSet(List<Row> rowList) {
    this.rowList = rowList;
    notifyDataSetChanged();
  }
}