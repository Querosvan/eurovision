import { TestBed, async } from '@angular/core/testing';
import { RouterTestingModule } from '@angular/router/testing';
import { CitylistComponent } from './citylist.component';
import { CitiesService } from 'src/app/services/cities.service';
import { getTestScheduler, cold } from 'jasmine-marbles';
import {Observable} from 'rxjs';
import { MatPaginator } from '@angular/material/paginator';
import {MatPaginatorModule} from '@angular/material/paginator';
import {CitiesDataSource} from '../../services/cities.datasource';

describe('CitylistComponent', () => {

  const citiesServiceStub = {
    findCities(page, size): Observable<any> {
      const cities$ = cold('--x|', { x: [{id: 1, name: 'Madrid'}, {id: 2, name: 'Sevilla'}] });
      return cities$;
    }
  } as CitiesService;

  const paginatorStub = {
    pageIndex: 0,
    pageSize: 5
  } as MatPaginator;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      imports: [
        RouterTestingModule,
        MatPaginatorModule
      ],
      declarations: [
        CitylistComponent
      ],
      providers: [{provide: CitiesService, useValue: citiesServiceStub}]
    }).compileComponents();
  }));

  it('should create the component', () => {
    const fixture = TestBed.createComponent(CitylistComponent);
    const app = fixture.componentInstance;
    expect(app).toBeTruthy();
  });

  it('ngOnInit', () => {
    const fixture = TestBed.createComponent(CitylistComponent);
    const app = fixture.componentInstance;
    app.ngOnInit();
    expect(app.dataSource).toBeTruthy();
  });

  it('loadCities called', () => {
    const fixture = TestBed.createComponent(CitylistComponent);
    const app = fixture.componentInstance;
    app.dataSource = new CitiesDataSource(citiesServiceStub);
    app.paginator = paginatorStub;
    const mySpy = spyOn(app.dataSource, 'loadCities');
    app.loadCitiesPage();
    expect(mySpy).toHaveBeenCalled();
  });
});
