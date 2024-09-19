import { useCallback, useReducer } from "react";

function useReducerWithThunk(
  reducer: React.Reducer<any, any>,
  initialState: object,
) {
  const [state, dispatch] = useReducer(reducer, initialState);

  function customDispatch(action: Function | object) {
    if (typeof action === "function") {
      return action(customDispatch);
    } else {
      dispatch(action);
    }
  }

  const stableDispatch = useCallback(customDispatch, [dispatch]);

  return [state, stableDispatch];
}

export { useReducerWithThunk };
