import { CitiesDataSource } from './cities.datasource';
import { CitiesService } from './cities.service';
import { getTestScheduler, cold } from 'jasmine-marbles';
import {Observable} from 'rxjs';
import { TestBed } from '@angular/core/testing';
import { CollectionViewer } from '@angular/cdk/collections';

describe('CitiesDataSource', () => {

  const citiesServiceStub = {
    findCities(page, number): Observable<any> {
      const cities$ = cold('--x', { x: [{id: 1, name: 'Madrid'}, {id: 2, name: 'Sevilla'}] });
      return cities$;
    }
  } as CitiesService;

  const collectionViewerStub = {} as CollectionViewer;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [ ],
      providers: [{provide: CitiesService, useValue: citiesServiceStub}]
    });
  });

  it('create an instance', () => {
      const dataSource = new CitiesDataSource(citiesServiceStub);
      expect(dataSource).toBeTruthy();
  });

  it('connect', () => {
    const dataSource = new CitiesDataSource(citiesServiceStub);

    const spy = spyOn(dataSource['citiesSubject'], 'asObservable');

    dataSource.connect(collectionViewerStub);

    expect(spy).toHaveBeenCalled();
  });

  it('disconnect', () => {
    const dataSource = new CitiesDataSource(citiesServiceStub);

    const spy = spyOn(dataSource['citiesSubject'], 'complete');

    dataSource.disconnect(collectionViewerStub);

    expect(spy).toHaveBeenCalled();
  });
});
