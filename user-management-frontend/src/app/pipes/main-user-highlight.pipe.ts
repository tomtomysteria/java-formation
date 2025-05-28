import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'mainUserHighlight'
})
export class MainUserHighlightPipe implements PipeTransform {

  transform(value: string): string {
    if (value.toLowerCase() === 'admin') {
      return `< ${value} >`;
    }
    return value;
  }

}
