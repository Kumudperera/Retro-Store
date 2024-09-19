"use client";
import {
  Breadcrumb,
  BreadcrumbItem,
  BreadcrumbLink,
  BreadcrumbList,
  BreadcrumbPage,
  BreadcrumbSeparator,
} from "@/components/ui/breadcrumb";
import { usePathname } from "next/navigation";
import { Fragment } from "react";
import { RSBreadcrumbAndTitleProps } from "./@types";

export default function RSBreadcrumbAndTitle({
  showTitle = true,
}: RSBreadcrumbAndTitleProps) {
  let title = "";
  const pathname = usePathname();
  const paths = pathname.split("/");
  const breadcrumbs = [];

  if (paths && paths.length > 0 && paths[0] === "") {
    let route = "";
    paths.shift();

    for (let i = 0; i < paths.length; i++) {
      const path = paths[i];
      const name = path.replace("-", " ");
      route += `/${path}`;
      const component =
        i !== paths.length - 1 ? (
          <BreadcrumbLink href={route}>{name}</BreadcrumbLink>
        ) : (
          <BreadcrumbPage>{name}</BreadcrumbPage>
        );

      if (i === paths.length - 1) title = name;
      breadcrumbs.push({
        route,
        name,
        component,
      });
    }
  } else return;

  return (
    <Fragment>
      <Breadcrumb className="hidden md:flex">
        <BreadcrumbList>
          {breadcrumbs.map((breadcrumb, index: number) => (
            <Fragment key={breadcrumb.name}>
              <BreadcrumbItem className="capitalize">
                {breadcrumb.component}
              </BreadcrumbItem>
              {index !== breadcrumbs.length - 1 && <BreadcrumbSeparator />}
            </Fragment>
          ))}
        </BreadcrumbList>
      </Breadcrumb>
      {showTitle && title && (
        <div className="flex items-center capitalize">
          <h1 className="text-lg font-semibold md:text-2xl">{title}</h1>
        </div>
      )}
    </Fragment>
  );
}
