int positionIndex;
LinearLayoutManager mLayoutManager;

Save the current state of recycle view position @onPause:

    positionIndex= mLayoutManager.findFirstVisibleItemPosition();
    View startView = rv.getChildAt(0);
    topView = (startView == null) ? 0 : (startView.getTop() - rv.getPaddingTop());


Restore the scroll position @onResume:

    if (positionIndex!= -1) {
        mLayoutManager.scrollToPositionWithOffset(positionIndex, topView);
    }



or another way can be @onPause:

long currentVisiblePosition = 0;
currentVisiblePosition = ((LinearLayoutManager)rv.getLayoutManager()).findFirstCompletelyVisibleItemPosition();




restore @onResume:

((LinearLayoutManager) rv.getLayoutManager()).scrollToPosition(currentVisiblePosition);
currentVisiblePosition = 0;
