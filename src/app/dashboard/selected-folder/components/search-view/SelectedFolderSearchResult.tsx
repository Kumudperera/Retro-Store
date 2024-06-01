"use client";

import {
  Table,
  TableBody,
  TableCell,
  TableHead,
  TableHeader,
  TableRow,
} from "@/components/ui/table";
import AppPagination from "@/components/pagination/AppPagination";
import { PageInfo } from "@/lib/@types";
import { Alert, AlertDescription } from "@/components/ui/alert";
import { useSelectedFolderSearch } from "../../contexts/SelectedFolderSearchProvider";
import { SelectedFolderAction } from "../../@types";
/* import {
  getUserAnnouncementByID,
  toggleFullSearchView,
} from "../../actions/UserAnnouncementApi"; */
import { Label } from "@radix-ui/react-label";

export default function SelectedFolderSearchResult() {
  const { state, dispatch } = useSelectedFolderSearch();

  const onPageDataChange = (data: PageInfo) => {
    dispatch({
      type: SelectedFolderAction.SET_PAGE_INFO,
      payload: data,
    });
  };

  /* const loadAnnouncementData = (announcementID: number) => {
    dispatch(getUserAnnouncementByID(announcementID));
    dispatch(toggleFullSearchView(false));
  }; */

  const pagedSelectedFolders = state.pagedSelectedFolders;
  const hasData =
    pagedSelectedFolders?.page_data && pagedSelectedFolders?.page_data.length > 0;

  return (
    <>
      {!hasData && (
        <Alert className="mt-2">
          <AlertDescription>No data found</AlertDescription>
        </Alert>
      )}
      {hasData && (
        <>
          <Table className={"w-full"}>
            <TableHeader>
              <TableRow>
                <TableHead className={"text-md text-blue-950"}>Title</TableHead>
                {/* {state.viewFullSearchView && (
                  <TableHead className={"text-md text-blue-950"}>
                    Description
                  </TableHead>
                )} */}
                <TableHead className={"text-md text-blue-950"}>Type</TableHead>
                {/* {state.viewFullSearchView && (
                  <TableHead className={"text-md text-blue-950"}>
                    Date
                  </TableHead>
                )} */}
              </TableRow>
            </TableHeader>
            <TableBody>
              {pagedSelectedFolders?.page_data.map((data) => (
                <TableRow
                  key={data.id}
                  onClick={(e) => {
                    e.preventDefault();
                    e.stopPropagation();
                    //loadAnnouncementData(data.id);
                  }}
                  className="hover:cursor-pointer"
                >
                  <TableCell>{data.folder_path}</TableCell>
                  <TableCell>
                    <Label
                      className={
                        "text-sm text-white bg-saiared rounded-full  px-3 py-1"
                      }
                    >
                      {data.status}
                    </Label>
                  </TableCell>
                  {/* {state.viewFullSearchView && (
                    <TableCell>{data.announcementDateStr}</TableCell>
                  )} */}
                </TableRow>
              ))}
            </TableBody>
          </Table>
          <AppPagination
            current_page={pagedSelectedFolders.current_page}
            total_pages={pagedSelectedFolders.total_pages}
            page_length={pagedSelectedFolders.page_length}
            onPaginationChange={onPageDataChange}
          />
        </>
      )}
    </>
  );
}
