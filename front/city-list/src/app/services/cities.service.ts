import {Injectable} from '@angular/core';
import {HttpClient, HttpParams} from '@angular/common/http';
import {Observable} from 'rxjs';

@Injectable()
export class CitiesService {

    constructor(private http: HttpClient) {}

    findCities(page = 0, size = 5): Observable<any> {

        return this.http.get('http://localhost:1111/api/cities/queryByPage', {
            params: new HttpParams()
                .set('page', page.toString())
                .set('size', size.toString())
        });
    }
}
