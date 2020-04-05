import { CitiesService } from './cities.service';
import { getTestScheduler, cold } from 'jasmine-marbles';
import {Observable} from 'rxjs';
import { TestBed } from '@angular/core/testing';
import { HttpClient } from '@angular/common/http';

describe('CitiesService', () => {

  const httpClientStub = {
    get(url, params): Observable<any> {
      const cities$ = cold('--x|', { x: [{id: 1, name: 'Madrid'}, {id: 2, name: 'Sevilla'}] });
      return cities$;
    }
  } as HttpClient;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [ ],
      providers: [{provide: HttpClient, useValue: httpClientStub}]
    });
  });

  it('create an instance', () => {
      const citiesService = new CitiesService(httpClientStub);
      expect(citiesService).toBeTruthy();
  });

  it('load cities', () => {
    const citiesService = new CitiesService(httpClientStub);

    const result = citiesService.findCities(0, 5);

    const expected = cold('--x|', { x: [{id: 1, name: 'Madrid'}, {id: 2, name: 'Sevilla'}] });

    expect(result).toBeObservable(expected);
  });

  it('load cities without params', () => {
    const citiesService = new CitiesService(httpClientStub);

    const result = citiesService.findCities();

    const expected = cold('--x|', { x: [{id: 1, name: 'Madrid'}, {id: 2, name: 'Sevilla'}] });

    expect(result).toBeObservable(expected);
  });
});
