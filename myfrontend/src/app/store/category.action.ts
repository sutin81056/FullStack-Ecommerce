import { createAction, props } from '@ngrx/store';

export const loadProductsByCategory = createAction(
  '[Product Section] Load Products By Category',
  props<{ categoryId: number }>()
);
