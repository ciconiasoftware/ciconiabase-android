package com.ciconia.android.base.search;

import io.reactivex.Observable;
import io.reactivex.subjects.PublishSubject;

public class RxSearchObservable {

    public static Observable<String> fromMaterialSearchView(MaterialSearchView searchView) {

        final PublishSubject<String> subject = PublishSubject.create();

        searchView.setOnQueryTextListener(new MaterialSearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                //subject.onComplete();
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                subject.onNext(newText);
                return false;
            }
        });

        return subject;
    }
}
