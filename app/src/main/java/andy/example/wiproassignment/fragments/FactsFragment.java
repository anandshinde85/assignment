package andy.example.wiproassignment.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import andy.example.commons.utils.AlertUtil;
import andy.example.model.entities.FactsResponse;
import andy.example.mvpviews.FactsContract;
import andy.example.presenter.FactsPresenter;
import andy.example.wiproassignment.R;
import andy.example.wiproassignment.adapters.FactsAdapter;
import andy.example.wiproassignment.listeners.FactsFragmentInteractionListener;

/**
 * Fragment to setup view for facts
 *
 * @author Anand Shinde
 */
public class FactsFragment extends BaseFragment implements FactsContract.View, SwipeRefreshLayout
    .OnRefreshListener {
  public final static String TAG = "FactsFragment";
  private FactsFragmentInteractionListener factsFragmentInteractionListener;
  private RecyclerView rvFacts;
  private ProgressBar progressBar;
  private TextView tvEmpty;
  private FactsContract.UserActionListener factsActionListener;
  private SwipeRefreshLayout swipeRefreshLayout;
  private FactsAdapter factsAdapter;

  public FactsFragment() {
    // Required empty public constructor
  }

  /**
   * Use this factory method to create a new instance of
   * this fragment using the provided parameters.
   */
  public static FactsFragment newInstance() {
    FactsFragment fragment = new FactsFragment();
    Bundle args = new Bundle();
    fragment.setArguments(args);
    return fragment;
  }

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    if (getArguments() != null) {
    }
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
                           Bundle savedInstanceState) {
    // Inflate the layout for this fragment
    View view = inflater.inflate(R.layout.fragment_facts, container, false);
    initUI(view);
    return view;
  }

  @Override
  public void onAttach(Context context) {
    super.onAttach(context);
    if (context instanceof FactsFragmentInteractionListener) {
      factsFragmentInteractionListener = (FactsFragmentInteractionListener) context;
    } else {
      throw new RuntimeException(context.toString()
          + " must implement FactsFragmentInteractionListener");
    }
  }

  @Override
  public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    fetchFacts();
  }

  @Override
  public void onDetach() {
    super.onDetach();
    factsFragmentInteractionListener = null;
  }

  /**
   * Method to initialize view for this fragment
   *
   * @param view - view reference
   */
  private void initUI(View view) {
    rvFacts = view.findViewById(R.id.rv_facts);
    tvEmpty = view.findViewById(R.id.tv_empty);
    progressBar = view.findViewById(R.id.progress_bar);
    swipeRefreshLayout = view.findViewById(R.id.swipe_refresh_layout);

    rvFacts.setLayoutManager(new LinearLayoutManager(getContext()));
    swipeRefreshLayout.setOnRefreshListener(this);
  }

  /**
   * Method to set refresh status for swipe refresh layout
   *
   * @param refreshing - true to display refreshing otherwise false
   */
  private void setRefreshing(boolean refreshing) {
    if (null == swipeRefreshLayout) {
      return;
    }
    if (refreshing) {
      swipeRefreshLayout.setRefreshing(refreshing);
      return;
    }
    if (swipeRefreshLayout.isRefreshing()) {
      swipeRefreshLayout.setRefreshing(false);
    }
  }

  /**
   * Method to fetch facts from API
   */
  private void fetchFacts() {
    if (null == factsActionListener) {
      factsActionListener = new FactsPresenter(this);
    }
    factsActionListener.fetchFacts();
  }

  /**
   * Called when a swipe gesture triggers a refresh.
   */
  @Override
  public void onRefresh() {
    setRefreshing(true);
    fetchFacts();
  }

  @Override
  public void showLoading(boolean show) {
    if (!isAdded()) {
      return;
    }
    if (show && swipeRefreshLayout.isRefreshing()) {
      return;
    } else if (show) {
      progressBar.setVisibility(View.VISIBLE);
    } else {
      if (swipeRefreshLayout.isRefreshing()) {
        setRefreshing(false);
      } else {
        progressBar.setVisibility(View.GONE);
      }
    }
  }

  @Override
  public void showNetworkUnavailableError() {
    if (!isAdded()) {
      return;
    }

    setRefreshing(false);

    AlertUtil alertUtil = new AlertUtil();
    alertUtil.showIndefiniteSnackBar(getView(), getString(R.string.no_network_msg), getString(R
        .string.retry), new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        fetchFacts();
      }
    });
  }

  /**
   * Method to handle success response.
   *
   * @param factsResponse - response from API
   */
  @Override
  public void onFactsSuccess(FactsResponse factsResponse) {
    if (!isAdded() || null == factsResponse) {
      return;
    }
    if (factsAdapter == null) {
      factsAdapter = new FactsAdapter(getContext(), factsResponse.getRows());
      rvFacts.setAdapter(factsAdapter);
    } else {
      factsAdapter.updateDataSet(factsResponse.getRows());
    }
    rvFacts.setVisibility(View.VISIBLE);
    tvEmpty.setVisibility(View.GONE);

    factsFragmentInteractionListener.updateToolbarTitle(factsResponse.getTitle());
  }

  /**
   * Method to handle failure response.
   */
  @Override
  public void onFactsFailure() {
    if (!isAdded()) {
      return;
    }

    AlertUtil alertUtil = new AlertUtil();
    alertUtil.showIndefiniteSnackBar(getView(), getString(R.string.error_msg), getString(R.string
        .retry), new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        fetchFacts();
      }
    });
  }

  /**
   * Method to handle UI when API returns empty data.
   */
  @Override
  public void showEmptyError() {
    if (!isAdded()) {
      return;
    }
    rvFacts.setVisibility(View.GONE);
    tvEmpty.setVisibility(View.VISIBLE);
  }
}