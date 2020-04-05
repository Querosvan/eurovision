import {CollectionViewer, DataSource} from '@angular/cdk/collections';
import {Observable, BehaviorSubject, of} from 'rxjs';
import {CitiesService} from './cities.service';
import {catchError, finalize} from 'rxjs/operators';
import { City } from '../model/city';

export class CitiesDataSource implements DataSource<City> {

    private citiesSubject = new BehaviorSubject<City[]>([]);

    private loadingSubject = new BehaviorSubject<boolean>(false);

    public loading$ = this.loadingSubject.asObservable();

    public totalElements = 0;

    constructor(private citiesService: CitiesService) {

    }

    loadCities(page: number, size: number) {

        this.loadingSubject.next(true);

        this.citiesService.findCities(page, size).pipe(
                catchError(() => of([])),
                finalize(() => this.loadingSubject.next(false))
            )
            .subscribe(response => {
              this.citiesSubject.next(response.content);
              this.totalElements = response.totalElements;
            });

    }

    connect(collectionViewer: CollectionViewer): Observable<City[]> {
        console.log('Connecting data source');
        return this.citiesSubject.asObservable();
    }

    disconnect(collectionViewer: CollectionViewer): void {
        this.citiesSubject.complete();
        this.loadingSubject.complete();
    }

}
