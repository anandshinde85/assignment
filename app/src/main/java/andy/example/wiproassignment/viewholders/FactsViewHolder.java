package andy.example.wiproassignment.viewholders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import andy.example.wiproassignment.R;

/**
 * View holder for Facts list
 *
 * @author Anand Shinde
 */
public class FactsViewHolder extends RecyclerView.ViewHolder {
  private TextView tvTitle;
  private TextView tvDescription;
  private ImageView ivFact;

  public FactsViewHolder(View itemView) {
    super(itemView);
    tvTitle = itemView.findViewById(R.id.tv_fact_title);
    tvDescription = itemView.findViewById(R.id.tv_fact_description);
    ivFact = itemView.findViewById(R.id.iv_fact_icon);
  }

  public TextView getTvTitle() {
    return tvTitle;
  }

  public void setTvTitle(TextView tvTitle) {
    this.tvTitle = tvTitle;
  }

  public TextView getTvDescription() {
    return tvDescription;
  }

  public void setTvDescription(TextView tvDescription) {
    this.tvDescription = tvDescription;
  }

  public ImageView getIvFact() {
    return ivFact;
  }

  public void setIvFact(ImageView ivFact) {
    this.ivFact = ivFact;
  }
}